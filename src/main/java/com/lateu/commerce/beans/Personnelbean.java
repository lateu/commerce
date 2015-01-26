/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.UserRole;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPersonnel;
import com.lateu.commerce.metier.sRole;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name = "pers")
@RequestScoped
public class Personnelbean implements Serializable {

    @ManagedProperty(value = "#{sPersonnel}")
    private sPersonnel serpersonnel;
    private Personnel selectedpersonnel = new Personnel();
    @ManagedProperty(value = "#{sRole}")
    private sRole servrole;
    private UserRole selectedRole = new UserRole();
    private String autorite;
    private List<Personnel> listeperson;
    private List<UserRole> listeRole;

    public Personnelbean() {
    }

    public String initAP() throws ServiceException {
        int res = serpersonnel.findAll().size();
        String s = null;
        if (res == 0) {
            selectedpersonnel.setDateNais(new Date());
            selectedpersonnel.setEtatCompte(1);
            selectedpersonnel.setNom("nom");
            selectedpersonnel.setPassword("admin");
            selectedpersonnel.setUsername("admin");
            selectedpersonnel.setPrenom("prenom");

            autorite = "ROLE_ADMIN";
            serpersonnel.create(selectedpersonnel, autorite);
            s = "b";
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("base de donnee Initialisée ", " "));

        }
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("base deja initialiser", " "));

        return s;


    }

    public String Save() throws ServiceException {

        /*  if(autorite.equals("ROLE_CAISSIER")){
         selectedpersonnel.setEtatCompte(0);
         }else{*/
        selectedpersonnel.setEtatCompte(1);
        // }

        serpersonnel.create(selectedpersonnel, autorite);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Enregistrement du personnel reussi ", " "));
        return "savepersonnel";
    }

    public String updatePersonnel() throws ServiceException {
        serpersonnel.updatePers(selectedpersonnel);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(" personnel mis à jour avec succés ", " "));

        return "updatePers";
    }

    public List<UserRole> getListeRole() throws ServiceException {
        return listeRole = servrole.findAll();
    }

    public void setListeRole(List<UserRole> listeRole) {
        this.listeRole = listeRole;
    }

    public String InitApplication(ActionEvent actionEvent) throws ServiceException {
        selectedpersonnel = new Personnel("inconnu", "inconnu", null, "00000", "lateu", "lateu", 1);
        // serpersonnel.create(selectedpersonnel, "admin0");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(" personnel mis à jour avec succés ", " "));

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));  
        return "init";
    }

    public sPersonnel getSerpersonnel() {
        return serpersonnel;
    }

    public void setSerpersonnel(sPersonnel serpersonnel) {
        this.serpersonnel = serpersonnel;
    }

    public Personnel getSelectedpersonnel() {
        return selectedpersonnel;
    }

    public void setSelectedpersonnel(Personnel selectedpersonnel) {
        this.selectedpersonnel = selectedpersonnel;
    }

    public String getAutorite() {
        return autorite;
    }

    public void setAutorite(String autorite) {
        this.autorite = autorite;
    }

    public List<Personnel> getListeperson() throws ServiceException {
        return serpersonnel.findAll();
    }

    public void setListeperson(List<Personnel> listeperson) {
        this.listeperson = listeperson;
    }

    public sRole getServrole() {
        return servrole;
    }

    public void setServrole(sRole servrole) {
        this.servrole = servrole;
    }

    public UserRole getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(UserRole selectedRole) {
        this.selectedRole = selectedRole;
    }
}
