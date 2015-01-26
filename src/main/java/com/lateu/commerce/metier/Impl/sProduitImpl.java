/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.dao.Fournisseurdao;
import com.lateu.commerce.dao.Personneldao;
import com.lateu.commerce.dao.Produitdao;
import com.lateu.commerce.dao.Stockdao;
import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sProduit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sProduitImpl implements sProduit {

    private Stockdao stockdao;
    private Fournisseurdao fournisseurdao;
    private Produitdao produitdao;
    private Personneldao personneldao;

    @Override
    public void save(Produit prod, String nom, String nom2,int qtinit) {
        try {
            Stock st = new Stock();
            Fournisseur f = fournisseurdao.findbyNom(nom);
            Personnel per = personneldao.findPersonnelByName(nom2);
            prod.setFournisseur(f);
            prod.setPersonnel(per);
            produitdao.create(prod);
            /**
             * on cree son stock
             */
            st.setCodeProduit(prod.getCode());
            st.setQuantiteEnStock(0);
            st.setProduit(prod);
            st.setQuantiteEnStock(qtinit);
            stockdao.create(st);

        } catch (DataAccessException ex) {
            Logger.getLogger(sProduitImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Produit> AllProduit() throws ServiceException {
        try {
            return produitdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sProduitImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     @Override
    public Produit findbyName(String nomprod) throws ServiceException {
        try {
            return produitdao.findbyName(nomprod);
        } catch (DataAccessException ex) {
            Logger.getLogger(sProduitImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Produitdao getProduitdao() {
        return produitdao;
    }

    public void setProduitdao(Produitdao produitdao) {
        this.produitdao = produitdao;
    }

    public Stockdao getStockdao() {
        return stockdao;
    }

    public void setStockdao(Stockdao stockdao) {
        this.stockdao = stockdao;
    }

    public Fournisseurdao getFournisseurdao() {
        return fournisseurdao;
    }

    public void setFournisseurdao(Fournisseurdao fournisseurdao) {
        this.fournisseurdao = fournisseurdao;
    }

    public Personneldao getPersonneldao() {
        return personneldao;
    }

    public void setPersonneldao(Personneldao personneldao) {
        this.personneldao = personneldao;
    }

    @Override
    public void upadate(Produit p, String nomFourn, String nomPers) throws ServiceException {
        try {       
            Produit tmp = new Produit();
            String x = null;
            tmp = produitdao.findById(p.getId());

            //     System.out.println("u=="+nomFourn);
            if (nomFourn.equals("choisir") == true) {
                p.setFournisseur(fournisseurdao.findById(tmp.getFournisseur().getId()));
                //  System.out.println("================aucun fourn choisie===="+p.getFournisseur().getNom());
            }else{
            p.setFournisseur( fournisseurdao.findbyNom(nomFourn));
            }

            if (nomPers.equals("") == true) {
                
                    p.setPersonnel(personneldao.findById(tmp.getPersonnel().getId()));
                    //  System.out.println("==========aucun personnel choisie========================="+p.getPersonnel().getUsername());
                
            }
            else{ 
                p.setPersonnel(personneldao.findPersonnelByName(nomPers));
            }

            produitdao.update(p);

        } catch (DataAccessException ex) {
           Logger.getLogger(sProduitImpl.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public List<Produit> findbyType(String t) throws ServiceException {
        try {
            produitdao.findbyType(t);


        } catch (DataAccessException ex) {
            Logger.getLogger(sProduitImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Produit> findbyFounisseur(String t) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit findbyID(Long id) throws ServiceException {
        try {
            return produitdao.findById(id);


        } catch (DataAccessException ex) {
            Logger.getLogger(sProduitImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
}
