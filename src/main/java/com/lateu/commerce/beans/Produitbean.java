/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Approvision;
import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sApprovision;
import com.lateu.commerce.metier.sFournisseur;
import com.lateu.commerce.metier.sPersonnel;
import com.lateu.commerce.metier.sProduit;
import com.lateu.commerce.metier.sStock;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author lateu
 */
@ManagedBean(name = "produit")
@RequestScoped
public class Produitbean implements Serializable {

    @ManagedProperty(value = "#{sProduit}")
    private sProduit servProduit;
    @ManagedProperty(value = "#{sPersonnel}")
    private sPersonnel servPersonnel;
    @ManagedProperty(value = "#{sStock}")
    private sStock servStock;
    @ManagedProperty(value = "#{sFournisseur}")
    private sFournisseur servFournisseur;
    private Approvision selectedapprov=new Approvision();
    @ManagedProperty(value = "#{sApprovision}")
    private sApprovision servapprov;
    private Personnel sectedpers = new Personnel();
    private Produit selectedprod = new Produit();
    private Produit prod_tmp = new Produit();
    private Fournisseur selectedfour = new Fournisseur();
    private List<Produit> listeprod;
    private List<Personnel> listeperson;
    private SelectItem[] listepersSelect;
    private SelectItem[] listefournSelect;
    private List<Fournisseur> listefourn;
    private String nomPers;
    private String nomFourn;
    private Stock stockUpd;

    public Produitbean() {
    }

    public SelectItem[] getListepersSelect() throws ServiceException {
        listeperson = servPersonnel.findAll();

        listepersSelect = new SelectItem[listeperson.size() + 1];
        listepersSelect[0] = new SelectItem("choisir");
        for (int i = 1; i < listeperson.size() + 1; i++) {
            listepersSelect[i] = new SelectItem(listeperson.get(i - 1).getNom());
        }
        return listepersSelect;
    }

    public SelectItem[] getListefournSelect() throws ServiceException {
        listefourn = servFournisseur.findAll();

        listefournSelect = new SelectItem[listefourn.size() + 1];
        listefournSelect[0] = new SelectItem("choisir");
        for (int i = 1; i < listefourn.size() + 1; i++) {
            listefournSelect[i] = new SelectItem(listefourn.get(i - 1).getNom());
        }


        return listefournSelect;
    }

    public String saveProduit() throws ServiceException {
        nomPers = servPersonnel.findByName(nomPers).getNom();
        nomFourn = servFournisseur.Findbynom(nomFourn).getNom();
        servProduit.save(selectedprod, nomFourn, nomPers,selectedapprov.getQuantite());
        //  System.out.println("fourn="+four.getNom());
       // selectedapprov.setQuantite(quantiteInit);
        servapprov.InitAprovision(selectedapprov, selectedprod.getDesignation());
        System.out.println("fourn="+selectedapprov);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Enregistrement du produit reussi !!", " "));
        return "saveproduit";
    }

    public String UpdateProduit() throws ServiceException {
        servProduit.upadate(selectedprod, nomFourn, nomPers);

        stockUpd = servStock.findMonStock(selectedprod.getId());

        stockUpd.setCodeProduit(selectedprod.getCode());
        stockUpd.setProduit(selectedprod);
        // System.out.println("===="+stockUpd);
        servStock.updateStock(stockUpd);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("prduit mis à jour avec succès !!", " "));

        return "updatProduit";
    }

    public String produitWarning() {
        return "produitWarning";

    }

    public String ExpireraBientot(Produit p) {

        // Calendar c = Calendar.getInstance();
        // Calendar today = Calendar.getInstance();
        // Date d = new Date();
        // c.setTime(p.getDatePeremption());
        // c.add(Calendar.MONTH, -2);

//        if (today.before(c)) {
//            return "expirera";
//        } else {
        return "expirera";
        // }
    }

    public sProduit getServProduit() {
        return servProduit;
    }

    public void setServProduit(sProduit servProduit) {
        this.servProduit = servProduit;
    }

    public sPersonnel getServPersonnel() {
        return servPersonnel;
    }

    public void setServPersonnel(sPersonnel servPersonnel) {
        this.servPersonnel = servPersonnel;
    }

    public sStock getServStock() {
        return servStock;
    }

    public void setServStock(sStock servStock) {
        this.servStock = servStock;
    }

    public sFournisseur getServFournisseur() {
        return servFournisseur;
    }

    public void setServFournisseur(sFournisseur servFournisseur) {
        this.servFournisseur = servFournisseur;
    }

    public Personnel getSectedpers() {
        return sectedpers;
    }

    public void setSectedpers(Personnel sectedpers) {
        this.sectedpers = sectedpers;
    }

    public Produit getSelectedprod() {
        return selectedprod;
    }

    public void setSelectedprod(Produit selectedprod) {
        this.selectedprod = selectedprod;
    }

    public Fournisseur getSelectedfour() {
        return selectedfour;
    }

    public void setSelectedfour(Fournisseur selectedfour) {
        this.selectedfour = selectedfour;
    }

    public List<Produit> getListeprod() throws ServiceException {
        return listeprod = servProduit.AllProduit();
    }

    public void setListeprod(List<Produit> listeprod) {
        this.listeprod = listeprod;
    }

    public List<Personnel> getListeperson() {
        return listeperson;
    }

    public void setListeperson(List<Personnel> listeperson) {
        this.listeperson = listeperson;
    }

    public List<Fournisseur> getListefourn() {
        return listefourn;
    }

    public void setListefourn(List<Fournisseur> listefourn) {
        this.listefourn = listefourn;
    }

    public String getNomPers() {
        return nomPers;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    public String getNomFourn() {
        return nomFourn;
    }

    public void setNomFourn(String nomFourn) {
        this.nomFourn = nomFourn;
    }

    public Produit getProd_tmp() {
        return prod_tmp;
    }

    public void setProd_tmp(Produit prod_tmp) {
        this.prod_tmp = prod_tmp;
    }

    public Stock getStockUpd() {
        return stockUpd;
    }

    public void setStockUpd(Stock stockUpd) {
        this.stockUpd = stockUpd;
    }

    public Approvision getSelectedapprov() {
        return selectedapprov;
    }

    public void setSelectedapprov(Approvision selectedapprov) {
        this.selectedapprov = selectedapprov;
    }

    public sApprovision getServapprov() {
        return servapprov;
    }

    public void setServapprov(sApprovision servapprov) {
        this.servapprov = servapprov;
    }

    public void setListepersSelect(SelectItem[] listepersSelect) {
        this.listepersSelect = listepersSelect;
    }

    public void setListefournSelect(SelectItem[] listefournSelect) {
        this.listefournSelect = listefournSelect;
    }
    
    
    
    
}
