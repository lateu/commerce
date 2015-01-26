/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.dao.Produitdao;
import java.util.List;

/**
 *
 * @author lateu
 */
public class ProduitdaoImpl extends GenericDao<Produit, Long> implements Produitdao{

    @Override
    public Produit findbyName(String n) throws DataAccessException {
    return  (  Produit) getManager().createNamedQuery("findbyName")
                .setParameter("nom", n)
                .getSingleResult();}
    
    @Override
    public List<Produit> findbyType(String t) throws DataAccessException {
   return   getManager().createNamedQuery("findbyType")
                .setParameter("type", t)
                .getResultList();}

    @Override
    public Produit findbyCode(String code) throws DataAccessException {
      return  (  Produit) getManager().createNamedQuery("findbyCode")
                .setParameter("code", code)
                .getSingleResult();}
    
    
    
}
