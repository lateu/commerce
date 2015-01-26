/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Produitdao;
import com.lateu.commerce.dao.Stockdao;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sStock;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sStockImpl implements sStock{
    private Stockdao stockdao;
    private Produitdao produitdao;

    public Stockdao getStockdao() {
        return stockdao;
    }

    public void setStockdao(Stockdao stockdao) {
        this.stockdao = stockdao;
    }

    public Produitdao getProduitdao() {
        return produitdao;
    }

    public void setProduitdao(Produitdao produitdao) {
        this.produitdao = produitdao;
    }

    @Override
    public void create(Stock s, Long l) throws ServiceException {
        try {
            Produit prd=produitdao.findById(l);
            
            s.setProduit(prd);
            stockdao.create(s);
        } catch (DataAccessException ex) {
            Logger.getLogger(sStockImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public Stock findbyCodeStock(String code) throws ServiceException{
        try {
            return stockdao.findbyCodeStock(code);
        } catch (DataAccessException ex) {
            Logger.getLogger(sStockImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

    
    
    @Override
    public Stock findMonStock(Long iden) throws ServiceException {
        try {
            return stockdao.findMonStock(iden);
        } catch (DataAccessException ex) {
            Logger.getLogger(sStockImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    
    
    @Override
    public List<Stock> findAll() throws ServiceException {
        try {
            return stockdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sStockImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

    @Override
    public void updateStock(Stock s) throws ServiceException {
         Produit tmp=new Produit();
        try {
             tmp=produitdao.findbyCode(s.getCodeProduit());
              s.setProduit(tmp);
            
          stockdao.update(s);
        } catch (DataAccessException ex) {
            Logger.getLogger(sStockImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
    }

    
    
}
