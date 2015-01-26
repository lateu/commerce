/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Stock;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sStock {

    public void create(Stock s, Long l) throws ServiceException;

    public void updateStock(Stock s) throws ServiceException;

    public Stock findbyCodeStock(String code) throws ServiceException;

    public Stock findMonStock(Long iden) throws ServiceException;

    public List<Stock> findAll() throws ServiceException;
}
