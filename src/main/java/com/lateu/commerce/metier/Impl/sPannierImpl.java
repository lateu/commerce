/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Pannierdao;
import com.lateu.commerce.dao.Produitdao;
import com.lateu.commerce.dao.Stockdao;
import com.lateu.commerce.entites.Pannier;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPannier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sPannierImpl implements sPannier {

    private Pannierdao pannierdao;
    private Produitdao produitdao;
    private Stockdao stockdao;
    //private Approvisiondao approvisiondao;
    // private Ventedao ventedao;

    @Override
    public void create(Pannier pan, Long prodid) throws ServiceException {
        Produit pr = new Produit();
        try {
            pr = produitdao.findById(prodid);
            pan.setProduit(pr);
            pan.setDesignation(pr.getDesignation());
            pan.setPU(pr.getPU());
            //pan.setQuantite(quantite);
            pan.setVeroux(0);
            pannierdao.create(pan);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPannierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pannier> AllPannier() throws ServiceException {
        try {
            return pannierdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sPannierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void clear(List<Pannier> lpan) throws ServiceException {
        try {
            lpan = pannierdao.findAll();
            for (Pannier pannier : lpan) {
                try {
                    pannierdao.delete(pannier);
                } catch (DataAccessException ex) {
                    Logger.getLogger(sPannierImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(sPannierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Pannierdao getPannierdao() {
        return pannierdao;
    }

    public void setPannierdao(Pannierdao pannierdao) {
        this.pannierdao = pannierdao;
    }

    public Produitdao getProduitdao() {
        return produitdao;
    }

    public void setProduitdao(Produitdao produitdao) {
        this.produitdao = produitdao;
    }

    @Override
    public int ValeurPannier(List<Pannier> lpanniers) throws ServiceException {
        int mt = 0;
        for (Pannier pannier : lpanniers) {
            mt = mt + pannier.getPT();
        }
        return mt;
    }

    @Override
    public void delete(Pannier pan) throws ServiceException {
        try {
            int q = 0, id = 0;
            Pannier p = pannierdao.findById(pan.getId());
            Stock s = stockdao.findMonStock(pan.getProduit().getId());
            q = s.getQuantiteEnStock() + pan.getQuantite();
            s.setQuantiteEnStock(q);
            // id=stockdao.
            stockdao.update(s);
            pannierdao.delete(p);
            //   ventedao.delete(null);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPannierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stockdao getStockdao() {
        return stockdao;
    }

    public void setStockdao(Stockdao stockdao) {
        this.stockdao = stockdao;
    }

    @Override
    public List<Pannier> Facture() throws ServiceException {
        return pannierdao.findFacture();
    }

   
}
