/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Fournisseurdao;
import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sFournisseur;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sFournisseurImpl implements sFournisseur{
    private Fournisseurdao fournisseurdao;
    @Override
 public void create(Fournisseur f) throws ServiceException {
        try {
            fournisseurdao.create(f);
        } catch (DataAccessException ex) {
            Logger.getLogger(sFournisseurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
   

    
       @Override
    public List<Fournisseur> findAll() throws ServiceException {
        try {
          return  fournisseurdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sFournisseurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;}
    
    @Override
    public Fournisseur Findbynom(String n) throws ServiceException {
        try {
           return fournisseurdao.findbyNom(n);
        } catch (DataAccessException ex) {
            Logger.getLogger(sFournisseurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    @Override
    public void update(Fournisseur f) throws ServiceException {
        try {
            fournisseurdao.update(f);
        } catch (DataAccessException ex) {
            Logger.getLogger(sFournisseurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    public Fournisseurdao getFournisseurdao() {
        return fournisseurdao;
    }

    public void setFournisseurdao(Fournisseurdao fournisseurdao) {
        this.fournisseurdao = fournisseurdao;
    }

    @Override
    public void delete(Long idfour) throws ServiceException {
        try {
            Fournisseur f=fournisseurdao.findById(idfour);
        } catch (DataAccessException ex) {
            Logger.getLogger(sFournisseurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

 
 


    
    
}
