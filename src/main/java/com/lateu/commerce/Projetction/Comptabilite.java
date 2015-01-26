/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.Projetction;

/**
 *
 * @author lateu
 */
public class Comptabilite {
    private String nomProduit;
    private int pu;
    private int quantite;

    public Comptabilite(String nomProduit, int pu, int quantite) {
        this.nomProduit = nomProduit;
        this.pu = pu;
        this.quantite = quantite;
    }

    public Comptabilite() {
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getPu() {
        return pu;
    }

    public void setPu(int pu) {
        this.pu = pu;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Comptabilite{" + "nomProduit=" + nomProduit + ", pu=" + pu + ", quantite=" + quantite + '}';
    }
    
    
}
