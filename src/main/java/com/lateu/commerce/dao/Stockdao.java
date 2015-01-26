/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.IDao;
import com.lateu.commerce.entites.Stock;

/**
 *
 * @author lateu
 */
public interface Stockdao extends IDao<Stock, Long>{
    
    public Stock findbyCodeStock(String c)throws DataAccessException;
    public Stock findMonStock(Long idProd)throws DataAccessException;
    
}
