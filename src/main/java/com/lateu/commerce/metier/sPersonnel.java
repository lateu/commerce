/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.Projetction.Comptabilite;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.UserRole;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sPersonnel {

    public void create(Personnel p, String user) throws ServiceException;
    
     public void updatePers(Personnel p) throws ServiceException;

    public List<Personnel> findAll() throws ServiceException;

    public Personnel findByName(String s) throws ServiceException;

    public UserRole findRole(String login) throws ServiceException;
    
    public List<Comptabilite> findComptab() throws ServiceException;
}
