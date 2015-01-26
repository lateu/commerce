/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier;

import com.lateu.commerce.entites.Produit;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public interface sProduit {

    public void save(Produit p, String l, String l2,int qtinit) throws ServiceException;

    public void upadate(Produit p, String nomPers, String nomFourn) throws ServiceException;

    public List<Produit> AllProduit() throws ServiceException;

    public List<Produit> findbyType(String t) throws ServiceException;

    public Produit findbyID(Long id) throws ServiceException;
public Produit findbyName(String nomprod) throws ServiceException;
    public List<Produit> findbyFounisseur(String t) throws ServiceException;
}
