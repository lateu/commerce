/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.Projetction.Comptabilite;
import com.lateu.commerce.dao.Personneldao;
import com.lateu.commerce.dao.UserRoledao;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.UserRole;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPersonnel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sPersonnelImpl implements sPersonnel{
    private Personneldao personneldao;
    private UserRoledao roledao;
   

    @Override
    public void create(Personnel p, String autority) throws ServiceException {
        try {
            UserRole user=new UserRole();
            user.setAutorié(autority);
            user.setPersonnel(p);
            personneldao.create(p);
            roledao.create(user);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    @Override
    public List<Personnel> findAll() throws ServiceException {
        try {
            return personneldao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }

    @Override
    public Personnel findByName(String s) throws ServiceException {
        try {
            return personneldao.findPersonnelByName(s);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }

    @Override
    public UserRole findRole(String login) throws ServiceException {
        try {
         return   personneldao.findRole(login);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    
     @Override
    public List<Comptabilite> findComptab() throws ServiceException {
        try {
            return personneldao.findComptablite();
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public Personneldao getPersonneldao() {
        return personneldao;
    }

    public void setPersonneldao(Personneldao personneldao) {
        this.personneldao = personneldao;
    }

    public UserRoledao getRoledao() {
        return roledao;
    }

    public void setRoledao(UserRoledao roledao) {
        this.roledao = roledao;
    }

    @Override
    public void updatePers(Personnel p) throws ServiceException {
        try {
            personneldao.update(p);
        } catch (DataAccessException ex) {
            Logger.getLogger(sPersonnelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

   
    
}
