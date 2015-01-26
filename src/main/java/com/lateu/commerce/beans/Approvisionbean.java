/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Approvision;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sApprovision;
import com.lateu.commerce.metier.sProduit;
import com.lateu.commerce.metier.sStock;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class Approvisionbean implements Serializable {

    @ManagedProperty(value = "#{sApprovision}")
    private sApprovision servApprovision;
    @ManagedProperty(value = "#{sProduit}")
    private sProduit servProduit;
    @ManagedProperty(value = "#{sStock}")
    private sStock servStock;
    private Approvision selectedapprovision = new Approvision();
    private Stock selectedstock;
    private Produit selectedProd;
    private String nomProd;
    private List<Produit> listeprod;
    public List<Approvision> listeApp;
    private SelectItem[] listeProdSelect;

    public SelectItem[] getListeProdSelect() throws ServiceException {
        listeprod = servProduit.AllProduit();
        listeProdSelect = new SelectItem[listeprod.size() + 1];
        listeProdSelect[0] = new SelectItem("choisir");
        for (int i = 1; i < listeprod.size() + 1; i++) {
            listeProdSelect[i] = new SelectItem(listeprod.get(i - 1).getDesignation());
        }
        return listeProdSelect;
    }

    public String buildCodeAp(Date d, String chaine) {
      String s;
        SimpleDateFormat tmp = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        s = tmp.format(dt);
        s = chaine.substring(0, 2)+""+s.substring(0, 2) + "" + s.substring(3, 5) + "" + s.substring(8, 10);
        return s;

    }

    public void create() throws ServiceException {
        int q = 0;
        selectedstock = servStock.findMonStock(servProduit.findbyName(nomProd).getId());
        selectedapprovision.setCodeApprovision(buildCodeAp(selectedapprovision.getDateJour(), nomProd));
        servApprovision.create(selectedapprovision, nomProd);
        q = selectedstock.getQuantiteEnStock() + selectedapprovision.getQuantite();
        selectedstock.setProduit(selectedstock.getProduit());
        selectedstock.setQuantiteEnStock(q);
        selectedstock.setCodeProduit(selectedstock.getCodeProduit());
        servStock.updateStock(selectedstock);
        // System.out.println("==="+buildCodeAp(selectedapprovision.getDateJour(), nomProd));

    }

    
  
//    public int Expirera2(Produit produit){
//        int r = 0;
//        
//            if (produit. == null) {
//          
//        }
//        return r;
//    }
//    
    
    public List<Approvision> getListeApp() throws ServiceException {
        return listeApp = servApprovision.findAll();
    }

    public void setListeApp(List<Approvision> listeApp) {
        this.listeApp = listeApp;
    }

    public Produit getSelectedProd() {
        return selectedProd;
    }

    public void setSelectedProd(Produit selectedProd) {
        this.selectedProd = selectedProd;
    }

    public List<Produit> getListeprod() {
        return listeprod;
    }

    public void setListeprod(List<Produit> listeprod) {
        this.listeprod = listeprod;
    }

    public void setListeProdSelect(SelectItem[] listeProdSelect) {
        this.listeProdSelect = listeProdSelect;
    }

    public sApprovision getServApprovision() {
        return servApprovision;
    }

    public void setServApprovision(sApprovision servApprovision) {
        this.servApprovision = servApprovision;
    }

    public sProduit getServProduit() {
        return servProduit;
    }

    public void setServProduit(sProduit servProduit) {
        this.servProduit = servProduit;
    }

    public sStock getServStock() {
        return servStock;
    }

    public void setServStock(sStock servStock) {
        this.servStock = servStock;
    }

    public Approvision getSelectedapprovision() {
        return selectedapprovision;
    }

    public void setSelectedapprovision(Approvision selectedapprovision) {
        this.selectedapprovision = selectedapprovision;
    }

    public Stock getSelectedstock() {
        return selectedstock;
    }

    public void setSelectedstock(Stock selectedstock) {
        this.selectedstock = selectedstock;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Approvisionbean() {
    }
}
