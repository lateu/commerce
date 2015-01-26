/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.commerce.Projetction.Comptabilite;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.dao.Personneldao;
import com.lateu.commerce.entites.UserRole;
import java.util.List;

/**
 *
 * @author lateu
 */
public class PersonneldaoImpl extends GenericDao<Personnel, Long> implements Personneldao {

    @Override
    public Personnel findPersonnelByName(String s) throws DataAccessException {
        return (Personnel) getManager().createNamedQuery("findPersonnelByName")
                .setParameter("nom", s)
                .getSingleResult();
    }

    @Override
    public UserRole findRole(String login) throws DataAccessException {
        return (UserRole) getManager().createNamedQuery("findRole")
                .setParameter("username", login)
                //.setParameter("password", pass)
                .getSingleResult();
    }

    @Override
    public List<Comptabilite> findComptablite() throws DataAccessException {
   return  getManager().createNamedQuery("ValeurEntreprise")
           .getResultList();
    }
}
