/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findbyName", query = "select p from Produit p where p.designation=:nom"),
    @NamedQuery(name = "findbyType", query = "select p from Produit p where (p.type=:type)"),
    @NamedQuery(name = "findbyCode", query = "select p from Produit p where (p.code=:code)"),
    @NamedQuery(name = "Produit.findByDesignation", query = "SELECT p FROM Produit p WHERE p.designation = :designation"),
    @NamedQuery(name = "Produit.findByType", query = "SELECT p FROM Produit p WHERE p.type = :type")
})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String designation;
    private String type;
    private int PU;
    private int seuil;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "produit", cascade = {CascadeType.ALL})
    private List<Pannier> pannier;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Personnel personnel;
    @OneToMany(mappedBy = "produit", cascade = {CascadeType.ALL})
    private List<Vente> vente;
    @OneToMany(mappedBy = "produit", cascade = {CascadeType.ALL})
    private List<Stock> stock;
    @OneToMany(mappedBy = "produit", cascade = {CascadeType.ALL})
    private List<Approvision> approvisions;

    public Produit() {
    }

    public Produit(String code, String designation, String type, int PU, int seuil, Fournisseur fournisseur, Personnel personnel) {
        this.code = code;
        this.designation = designation;
        this.type = type;
        this.PU = PU;
        this.seuil = seuil;
        this.fournisseur = fournisseur;
        this.personnel = personnel;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPU() {
        return PU;
    }

    public void setPU(int PU) {
        this.PU = PU;
    }

    public List<Pannier> getPannier() {
        return pannier;
    }

    public void setPannier(List<Pannier> pannier) {
        this.pannier = pannier;
    }

    public List<Vente> getVente() {
        return vente;
    }

    public void setVente(List<Vente> vente) {
        this.vente = vente;
    }

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public List<Approvision> getApprovisions() {
        return approvisions;
    }

    public void setApprovisions(List<Approvision> approvisions) {
        this.approvisions = approvisions;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", code=" + code + ", designation=" + designation + ", type=" + type + ", PU=" + PU + ", seuil=" + seuil + '}';
    }
}
