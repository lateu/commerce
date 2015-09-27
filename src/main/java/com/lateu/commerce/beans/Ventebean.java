/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Pannier;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Vente;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPannier;
import com.lateu.commerce.metier.sProduit;
import com.lateu.commerce.metier.sVente;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author lateu
 */
@ManagedBean(name = "ventebean")
@RequestScoped
public class Ventebean implements Serializable {

    @ManagedProperty(value = "#{sVente}")
    private sVente servVente;
    @ManagedProperty(value = "#{sProduit}")
    private sProduit servProduit;
    @ManagedProperty(value = "#{sPannier}")
    private sPannier serPannier;
    private Vente vente = new Vente();
    private Produit produit = new Produit();
    private Pannier pannier = new Pannier();
    private String codeProduit;
    List<Vente> listevente;
    List<Pannier> panniers;
    private int montant;
    int recettejour;
    int recetteperiodique;
    private Date d1;
    private Date d2;
    private boolean testImp = false;

    public Ventebean() {
    }

    public void Save(ActionEvent actionEvent) throws ServiceException {
        int sentinel = servVente.VenteValide(codeProduit, vente.getQuantite());

        if (vente.getQuantite() > 0) {

            if (sentinel == 1) {
                servVente.create(vente, codeProduit);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("produit ajouté au pannier", " "));
                //   return "afficherPannier";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "quantité non disponible", ""));

            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "quantité  invalide", ""));


        }
    }

    public void diminuerPannier() throws ServiceException {
        // System.out.println("===="+getPannier());

        serPannier.delete(pannier);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("produit ajouté au pannier", " "));
    }

    public int getMontant() throws ServiceException {
        panniers = serPannier.AllPannier();
        return montant = serPannier.ValeurPannier(panniers);
    }

    public List<Pannier> getPanniers() throws ServiceException {
        return panniers = serPannier.AllPannier();
    }

    public void setPanniers(List<Pannier> panniers) {
        this.panniers = panniers;
    }

    public String vider() {
        try {
            panniers = serPannier.AllPannier();
            serPannier.clear(panniers);

            return "panniervide";
        } catch (ServiceException ex) {
            Logger.getLogger(Pannierbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void enregistrerPannier() throws ServiceException {
        int r;
        r = servVente.SavePanniers(servVente.NoneSave());
        if (r != 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " vente sauvegardée", ""));
            testImp = true;
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " aucune vente sauvegardée", ""));

        }


    }

    public void recette() throws ServiceException {
        servVente.recetteDuJour(d1);

    }

    public void recettep() throws ServiceException {
        servVente.recettePeriode(d1, d2);

    }

    public int getRecettejour() throws ServiceException {
        return servVente.recetteDuJour(d1);
    }

    public void setRecettejour(int recettejour) {
        this.recettejour = recettejour;
    }

    public int getRecetteperiodique() throws ServiceException {
        return servVente.recettePeriode(d1, d2);
    }

    public void setRecetteperiodique(int recetteperiodique) {
        this.recetteperiodique = recetteperiodique;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Vente> getListevente() throws ServiceException {
        return servVente.findAll();
    }

    public void setListevente(List<Vente> listevente) {
        this.listevente = listevente;
    }

    public sVente getServVente() {
        return servVente;
    }

    public void setServVente(sVente servVente) {
        this.servVente = servVente;
    }

    public sProduit getServProduit() {
        return servProduit;
    }

    public void setServProduit(sProduit servProduit) {
        this.servProduit = servProduit;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public sPannier getSerPannier() {
        return serPannier;
    }

    public void setSerPannier(sPannier serPannier) {
        this.serPannier = serPannier;
    }

    public Pannier getPannier() {
        return pannier;
    }

    public void setPannier(Pannier pannier) {
        this.pannier = pannier;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public boolean isTestImp() {
        return testImp;
    }

    public void setTestImp(boolean testImp) {
        this.testImp = testImp;
    }
}
