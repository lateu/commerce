/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Fournisseur;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sFournisseur {
    public void create(Fournisseur f)throws ServiceException;
     public void update(Fournisseur f)throws ServiceException;
     public List<Fournisseur> findAll()throws ServiceException;
      public Fournisseur Findbynom(String n)throws ServiceException;
      
      public void delete(Long idfour)throws ServiceException;
    
}
