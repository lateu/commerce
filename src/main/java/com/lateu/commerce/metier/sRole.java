/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.UserRole;
import java.util.List;
import javax.management.relation.Role;

/**
 *
 * @author lateu
 */
public interface sRole {
    public void create(UserRole u)throws ServiceException;
    public List<UserRole> findAll()throws ServiceException;
    
}
