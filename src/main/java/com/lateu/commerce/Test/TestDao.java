/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.Test;

import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.entites.Vente;
import com.lateu.commerce.metier.ServiceException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author lateu
 */
public class TestDao {

    public static void main(String[] args) throws ServiceException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("commercePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Fournisseur f = new Fournisseur();
        f.setContact("75-39-97-23");
        f.setNom("SODECOTON");
        f.setVille("maroua");
        Date d = new Date();
//        Personnel p = new Personnel("lappa", "vendeur",d, "admin");
//        UserRole user=new UserRole("lateu", "lateu", "ok", p);
//      
        //  Produit p2 = new Produit("S025", "SOYOR", "alimentaire", 200, 20, d, d, d, f, p);
        //servper.create(p,user);
        //servf.create(f);
        Vente v = new Vente(10, d);
        // servprod.save(p2, "SODECOTON", "lateu");
        // servV.create(v, "SOYOR");
        // List<Vente> l= servV.findByProdType("electromenager");

        //System.out.println( servV.recetteDuJour(d));  
        //  }
        // servPan.clear();
//        em.persist(f);
//        em.persist(p);
//        em.persist(p2);
        tx.commit();
        em.close();

    }
}
