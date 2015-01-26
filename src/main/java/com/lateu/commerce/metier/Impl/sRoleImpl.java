/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Personneldao;
import com.lateu.commerce.dao.UserRoledao;
import com.lateu.commerce.entites.UserRole;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sRole;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Role;

/**
 *
 * @author lateu
 */
public class sRoleImpl implements sRole{
private UserRoledao roledao;
private Personneldao personneldao;
    @Override
    public void create(UserRole u) throws ServiceException {
    try {
        roledao.create(u);
    } catch (DataAccessException ex) {
        Logger.getLogger(sRoleImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public UserRoledao getRoledao() {
        return roledao;
    }

    public void setRoledao(UserRoledao roledao) {
        this.roledao = roledao;
    }

    public Personneldao getPersonneldao() {
        return personneldao;
    }

    public void setPersonneldao(Personneldao personneldao) {
        this.personneldao = personneldao;
    }

    @Override
    public List<UserRole> findAll() throws ServiceException {
    try {
        return roledao.findAll();
    } catch (DataAccessException ex) {
        Logger.getLogger(sRoleImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
    
    
    
    
}
