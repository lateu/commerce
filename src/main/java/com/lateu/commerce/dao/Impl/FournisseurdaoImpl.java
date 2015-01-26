/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao.Impl;
import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.dao.Fournisseurdao;

/**
 *
 * @author lateu
 */
public class FournisseurdaoImpl extends GenericDao<Fournisseur, Long> implements Fournisseurdao {

    @Override
    public Fournisseur findbyNom(String nom) throws DataAccessException {
        return  ( Fournisseur) getManager().createNamedQuery("findbyNom")
                .setParameter("nom", nom)
                .getSingleResult();
        }
    
}
