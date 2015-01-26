/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.Test;

import com.lateu.commerce.entites.Approvision;
import com.lateu.commerce.entites.Fournisseur;
import com.lateu.commerce.entites.Personnel;
import com.lateu.commerce.entites.Produit;
import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.entites.UserRole;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sApprovision;
import com.lateu.commerce.metier.sFournisseur;
import com.lateu.commerce.metier.sPannier;
import com.lateu.commerce.metier.sPersonnel;
import com.lateu.commerce.metier.sProduit;
import com.lateu.commerce.metier.sStock;
import com.lateu.commerce.metier.sVente;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lateu
 */
public class TestService {
     private static sPersonnel servper;
    private static sProduit servprod;
    private static sFournisseur servf;
    private static sVente servV;
    private static sPannier servPan;
    private static sStock servstock;
     private static sApprovision servAppro;

    public static void main(String[] args) throws ServiceException {
      
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
        servper = (sPersonnel) ctx.getBean("sPersonnel");
        servprod = (sProduit) ctx.getBean("sProduit");
        servf = (sFournisseur) ctx.getBean("sFournisseur");
        servV = (sVente) ctx.getBean("sVente");
        servPan = (sPannier) ctx.getBean("sPannier");
        servstock=(sStock)ctx.getBean("sStock");
         servAppro=(sApprovision)ctx.getBean("sApprovision");
        Fournisseur f = new Fournisseur();
        f.setContact("75-39-97-23");
        f.setNom("SODECOTON");
        f.setVille("maroua");
        Date d = new Date();
        Personnel p = new Personnel("lappa", "bert", d, "4012", "caissier", "caissier", 1);
         Personnel per = new Personnel("maba", "solange", d, "5212", "comptable", "comptable", 1);
        UserRole user=new UserRole("admin", p);
      
       Produit p2 = new Produit("B120", "banane", "alimentaire",200, 0, f, p);
        //System.out.println("=="+servstock.findMonStock(1L));
       // System.out.println(servper.findComptab());
        Approvision ap=new Approvision("MA4512", d,d,d, 45);
      //  servAppro.create(ap, "banane");
        //
        servper.create(per,"ROLE_ADMIN");
//        servf.create(f);
       // servprod.save(p2, "SODECOTON", "lateu");
//        servAppro.create(ap,"B120");
//        
        
        
       // Vente v = new Vente(10, d);
       
          // servV.create(v, "SOYOR");
        // List<Vente> l= servV.findByProdType("electromenager");

        //System.out.println( servV.recetteDuJour(d));  
        //  }
       // servPan.clear();

        
    }
}
