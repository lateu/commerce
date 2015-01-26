/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.metier.Impl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.commerce.dao.Approvisiondao;
import com.lateu.commerce.dao.Produitdao;
import com.lateu.commerce.entites.Approvision;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sApprovision;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class sApprovisionImpl implements sApprovision {

    private Produitdao produitdao;
    private Approvisiondao approvisiondao;

    @Override
    public void create(Approvision ap, String codeprod) throws ServiceException {
        try {
            Produit p = produitdao.findbyName(codeprod);
            ap.setProduit(p);
            approvisiondao.create(ap);


        } catch (DataAccessException ex) {
            Logger.getLogger(sApprovisionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String BuildCode(Date d, String ch) throws ServiceException {
        String s;
        SimpleDateFormat tmp = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        s = tmp.format(dt);
        s = ch.substring(0, 2) + "" + s.substring(0, 2) + "" + s.substring(3, 5) + "" + s.substring(8, 10);
        return s;

    }

    @Override
    public void InitAprovision(Approvision initApp, String nomProd) throws ServiceException {
        try {
            Produit p = produitdao.findbyName(nomProd);
            initApp.setProduit(p);
            initApp.setCodeApprovision(BuildCode(initApp.getDateJour(), p.getDesignation()));
            approvisiondao.create(initApp);
        } catch (DataAccessException ex) {
            Logger.getLogger(sApprovisionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Approvision ap) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Approvision ap) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Approvision> findByPeriode(Date debut, Date fin) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Approvision> findAll() throws ServiceException {
        try {
            return approvisiondao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(sApprovisionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Produitdao getProduitdao() {
        return produitdao;
    }

    public void setProduitdao(Produitdao produitdao) {
        this.produitdao = produitdao;
    }

    public Approvisiondao getApprovisiondao() {
        return approvisiondao;
    }

    public void setApprovisiondao(Approvisiondao approvisiondao) {
        this.approvisiondao = approvisiondao;
    }
}
