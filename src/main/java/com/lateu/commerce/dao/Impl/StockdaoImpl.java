/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.dao.Stockdao;

/**
 *
 * @author lateu
 */
public class StockdaoImpl extends GenericDao<Stock, Long> implements Stockdao{
    @Override
    public Stock findbyCodeStock(String c) throws DataAccessException {
     return  (  Stock) getManager().createNamedQuery("findbyCodeStock")
                .setParameter("code", c)
                .getSingleResult(); }

    @Override
    public Stock findMonStock(Long idp) throws DataAccessException {
       return  (  Stock) getManager().createNamedQuery("findMonStock")
                .setParameter("idp", idp)
                .getSingleResult();
    
    }
    
}
