/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByType", query = "select v from Vente v,Produit p join v.produit p_v where p.id=p_v.id and p.type=:type"),
    @NamedQuery(name = "findrecetteJournaliere", query = "select v from Vente v where  v.dateRecette=:date"),
    @NamedQuery(name = "findrecettePeriodique", query = "select v from Vente v where  v.dateRecette between :d1 and :d2"),
    @NamedQuery(name = "venteProduit", query = "select v from Vente v,Produit p  where p.type=:type AND p.id=v.produit"),
     @NamedQuery(name = "ValeurEntreprise", query = " select new com.lateu.commerce.Projetction.Comptabilite(p.designation,p.PU,s.quantiteEnStock) from Stock s, Produit p JOIN s.produit s_prod WHERE (p.id=s_prod.id)"),
})
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //  private String designation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Produit produit;

    public Vente() {
    }
    private int quantite;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRecette;

    public Vente(int quantite, Date dateRecette) {

        this.quantite = quantite;
        this.dateRecette = dateRecette;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateRecette() {
        return dateRecette;
    }

    public void setDateRecette(Date dateRecette) {
        this.dateRecette = dateRecette;
    }

    @Override
    public String toString() {
        return "Vente{" + "id=" + id  + ", quantite=" + quantite + ", dateRecette=" + dateRecette + '}';
    }
    
    
    
}
