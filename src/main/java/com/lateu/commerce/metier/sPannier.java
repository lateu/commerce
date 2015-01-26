/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Pannier;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sPannier {
    public void create(Pannier pan,Long prodid)throws ServiceException;
    public void clear(List<Pannier> lpan)throws ServiceException;
     public void delete(Pannier pan)throws ServiceException;
     public List<Pannier> Facture()throws ServiceException;
    public List<Pannier> AllPannier()throws ServiceException;
    public int  ValeurPannier(List<Pannier> lpanniers)throws ServiceException;
    
}
