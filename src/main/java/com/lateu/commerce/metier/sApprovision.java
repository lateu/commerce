/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Approvision;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sApprovision {
    public void create(Approvision ap,String codeprod) throws ServiceException;
    public String BuildCode(Date d, String ch)throws ServiceException;
    public void InitAprovision(Approvision initApp,String codeAP)throws ServiceException;
    public void update(Approvision ap) throws ServiceException;

    public void delete(Approvision ap) throws ServiceException;

    public List<Approvision> findByPeriode(Date debut, Date fin) throws ServiceException;
    
    public List<Approvision> findAll() throws ServiceException;
}
