/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.IDao;
import com.lateu.commerce.Projetction.Comptabilite;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.UserRole;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Personneldao extends IDao<Personnel, Long>  {
    public Personnel findPersonnelByName(String s)throws DataAccessException;
     public UserRole findRole(String login)throws DataAccessException;
      List <Comptabilite> findComptablite() throws DataAccessException;

    
}
