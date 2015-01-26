/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author lateu
 */
@Entity
public class UserRole {
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String autorié;
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Personnel personnel;

  
    public UserRole() {
    }

    public UserRole(String autorié, Personnel personnel) {
        this.autorié = autorié;
        this.personnel = personnel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutorié() {
        return autorié;
    }

    public void setAutorié(String autorié) {
        this.autorié = autorié;
    }
    
    
    
 
    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

  
    
    
}
