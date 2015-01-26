/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.IDao;
import com.lateu.commerce.entites.Produit;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Produitdao extends IDao<Produit, Long> {
    public Produit findbyName(String n)throws DataAccessException;
     public Produit findbyCode(String code)throws DataAccessException;
    List<Produit> findbyType(String t)throws DataAccessException;
}
