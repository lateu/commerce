/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Pannierdao;
import com.lateu.commerce.dao.Produitdao;
import com.lateu.commerce.dao.Stockdao;
import com.lateu.commerce.dao.Ventedao;
import com.lateu.commerce.entites.Pannier;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.entites.Vente;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sVente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sVenteImpl implements sVente {

    private Ventedao ventedao;
    private Stockdao stockdao;
    private Produitdao produitdao;
    private Pannierdao pannierdao;
/**
 * verifi si la quantité demandée est disponible.
 * @param codeProd
 * @param quantite
 * @return
 * @throws ServiceException 
 */
    @Override
    public int VenteValide(String codeProd, int quantite) throws ServiceException {
        try {
            Produit prod = new Produit();
            Stock st = new Stock();
            int r;
            prod = produitdao.findbyCode(codeProd);
            st = stockdao.findbyCodeStock(prod.getCode());
            r = st.getQuantiteEnStock() - quantite;
            if (r > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
/**
 * ajoute un element dans le pannier apres avoir diminuer son stock
 * @param v
 * @param codeprod
 * @throws ServiceException 
 */
    @Override
    public void create(Vente v, String codeprod) throws ServiceException {
        Produit prod = new Produit();
        Stock st = new Stock();
        Pannier pan = new Pannier();
        // String code;
        Date d = new Date();
        //Vente v=new Vente();
        int r;
        try {
            prod = produitdao.findbyCode(codeprod);
       
           // v.setProduit(prod);
           // v.setDateRecette(d);
           // ventedao.create(v);
            //**************on diminu le stock******************//
            st = stockdao.findbyCodeStock(prod.getCode());

            r = st.getQuantiteEnStock();
            r -= v.getQuantite();
            st.setQuantiteEnStock(r);
            stockdao.update(st);
//********************on ajoute au pannier*****************//
            pan.setProduit(prod);
            pan.setDesignation(prod.getDesignation());
            pan.setPU(prod.getPU());
            int pt = v.getQuantite() * prod.getPU();
            pan.setPT(pt);
            pan.setQuantite(v.getQuantite());
            pannierdao.create(pan);

        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stockdao getStockdao() {
        return stockdao;
    }

    public void setStockdao(Stockdao stockdao) {
        this.stockdao = stockdao;
    }

    public Pannierdao getPannierdao() {
        return pannierdao;
    }

    public void setPannierdao(Pannierdao pannierdao) {
        this.pannierdao = pannierdao;
    }

    public Ventedao getVentedao() {
        return ventedao;
    }

    public void setVentedao(Ventedao ventedao) {
        this.ventedao = ventedao;
    }

    public Produitdao getProduitdao() {
        return produitdao;
    }

    public void setProduitdao(Produitdao produitdao) {
        this.produitdao = produitdao;
    }

    @Override
    public List<Vente> findByProdType(String type) throws ServiceException {
        try {
            return ventedao.findByType(type);
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * fourni la recette du jour
     * @param d
     * @return
     * @throws ServiceException 
     */

    @Override
    public int recetteDuJour(Date d) throws ServiceException {
        try {
            int qte, pu, total = 0;
            List<Vente> l = ventedao.findrecetteJournaliere(d);
            for (Vente vente : l) {
                qte = vente.getQuantite();
                pu = vente.getProduit().getPU();
                total += pu * qte;
            }
            return total;
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int recettePeriode(Date d1, Date d2) throws ServiceException {
        int qte, pu, total = 0;
        List<Vente> l = new ArrayList<Vente>();
        try {
            l = ventedao.findrecettePeriodique(d1, d2);
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Vente vente : l) {
            qte = vente.getQuantite();
            pu = vente.getProduit().getPU();
            total += pu * qte;
        }
        return total;
    }

    @Override
    public List<Vente> findAll() throws ServiceException {
        try {
            return ventedao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Vente> ventsproduit(String type) throws ServiceException {
        try {
            return ventedao.venteProduit(type);
        } catch (DataAccessException ex) {
            Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
     @Override
    public int SavePanniers(List<Pannier> lpanniers) throws ServiceException {
        Vente v = new Vente();
        for (Pannier pannier : lpanniers) {
            try {
                v.setQuantite(pannier.getQuantite());
                v.setDateRecette(new Date());
                v.setProduit(pannier.getProduit());
               ventedao.create(v);
                pannier.setVeroux(1);
                pannierdao.update(pannier);
            } catch (DataAccessException ex) {
                Logger.getLogger(sVenteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lpanniers.size();
    }

    @Override
    public List<Pannier> NoneSave() throws ServiceException {
  
    return pannierdao.NoneSave();
    }
}
