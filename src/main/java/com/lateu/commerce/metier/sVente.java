/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Pannier;
import com.lateu.commerce.entites.Vente;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface sVente {

    public void create(Vente V, String nomProd) throws ServiceException;

    public List<Vente> findByProdType(String type) throws ServiceException;

    public int recetteDuJour(Date d) throws ServiceException;

    public int recettePeriode(Date d1, Date d2) throws ServiceException;

    public List<Vente> findAll() throws ServiceException;

    public List<Vente> ventsproduit(String type) throws ServiceException;
    
    public int VenteValide(String codeProd, int quantite) throws ServiceException;
    
     public int  SavePanniers(List<Pannier> lpanniers)throws ServiceException;
      public List<Pannier>  NoneSave()throws ServiceException;
}
