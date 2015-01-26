/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sFournisseur;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lateu
 */
@ManagedBean(name = "fourn")
@RequestScoped
public class Fournisseurbean implements Serializable{

    @ManagedProperty(value = "#{sFournisseur}")
    private sFournisseur serFournisseur;
   // @ManagedProperty(value = "#{Fournisseur}")
    private Fournisseur  selectedfour = new Fournisseur();
    private List<Fournisseur> fournisseurs;
    public Fournisseurbean() {
       
    }

    public String Save() throws ServiceException {
        serFournisseur.create(selectedfour);
         FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Enregistrement du fournisseur reussi !!", " "));
        return "savefournisseur";
    }

    public sFournisseur getSerFournisseur() {
        return serFournisseur;
    }

    public void setSerFournisseur(sFournisseur serFournisseur) {
        this.serFournisseur = serFournisseur;
    }

    public Fournisseur getSelectedfour() {
        return selectedfour;
    }

    public void setSelectedfour(Fournisseur selectedfour) {
        this.selectedfour = selectedfour;
    }

   public String update() throws ServiceException{
       System.out.println("======"+selectedfour);
       serFournisseur.update(selectedfour);
       FacesContext context = FacesContext.getCurrentInstance();

       context.addMessage(null, new FacesMessage("mise à jour du fournisseur reussie !!", " "));
       return "updateFour";
   }

   public String deletefourn(){
       System.out.println("four à sup="+selectedfour);
   
   return "deletefourn";
   }
   
   
    public List<Fournisseur> getFournisseurs() throws ServiceException {
        
         fournisseurs=serFournisseur.findAll();
        // System.out.println("fournisseur="+fournisseurs.size());
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    
//    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException, com.itextpdf.text.DocumentException {  
//    Document pdf = (Document) document;  
//    pdf.open();  
//    pdf.setPageSize(PageSize.A4);  
//  
//    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
//    String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";  
//  
//    pdf.add(Image.getInstance(logo));  
//}  
//    
}
