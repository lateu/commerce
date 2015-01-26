/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.commerce.dao.Pannierdao;
import com.lateu.commerce.entites.Pannier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class PannierdaoImpl extends GenericDao<Pannier, Long> implements Pannierdao{

    @Override
    public List<Pannier> findFacture() {
        try {
            return  getManager().createNamedQuery("findCurrentPannier")
                      // .setParameter("nom", nom)
                       .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(PannierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    
    }

//    @Override
//    public void vider() throws DataAccessException {
//     getManager().createNamedQuery("clear")
//               // .setParameter("nom", nom)
//                .getSingleResult();
//    }

    @Override
    public List<Pannier> NoneSave() {
   
      try {
            return  getManager().createNamedQuery("NoneSave")
                      // .setParameter("nom", nom)
                       .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(PannierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    
    }
    
}
