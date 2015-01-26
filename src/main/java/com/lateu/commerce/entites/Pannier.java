/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "clear", query = "delete  from Pannier  "),
      @NamedQuery(name = "findCurrentPannier", query = "select p from Pannier p where p.veroux=1"),
          @NamedQuery(name = "NoneSave", query = "select p from Pannier p where p.veroux=0"),
   })
public class Pannier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Produit produit;
    private String designation;
    private int PU;
    private int quantite;
    private int PT;
    private int veroux;

    public Pannier(Produit produit, String designation, int PU, int quantite, int PT, int veroux) {
        this.produit = produit;
        this.designation = designation;
        this.PU = PU;
        this.quantite = quantite;
        this.PT = PT;
        this.veroux = veroux;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getVeroux() {
        return veroux;
    }

    public void setVeroux(int veroux) {
        this.veroux = veroux;
    }

   
    public void setProduit(Produit produit) {
        this.produit = produit;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPT() {
        return PT;
    }

    public void setPT(int PT) {
        this.PT = PT;
    }

    public Pannier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pannier{" + "id=" + id + ", designation=" + designation + ", PU=" + PU + ", quantite=" + quantite + ", PT=" + PT + '}';
    }
    
    
    
    
}
