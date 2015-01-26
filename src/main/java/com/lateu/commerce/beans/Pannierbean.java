/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Pannier;
import com.lateu.commerce.entites.Vente;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sPannier;
import com.lateu.commerce.metier.sVente;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author richardlateu
 */
@ManagedBean(name = "pannier")
@RequestScoped
public class Pannierbean implements Serializable {

    @ManagedProperty(value = "#{sPannier}")
    private sPannier servPannier;
    @ManagedProperty(value = "#{sVente}")
    private sVente servVente;
    @ManagedProperty(value = "#{Pannier}")
    private Pannier pan;
    private List<Pannier> panniers;
    private int montant;
    Vente v = new Vente();

    public Pannierbean() {
        pan = new Pannier();
    }

    public String vider() {
        try {
            panniers = servPannier.AllPannier();
            servPannier.clear(panniers);

            return "panniervide";
        } catch (ServiceException ex) {
            Logger.getLogger(Pannierbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public sPannier getServPannier() {
        return servPannier;
    }

    public void setServPannier(sPannier servPannier) {
        this.servPannier = servPannier;
    }

    public List<Pannier> getPanniers() throws ServiceException {
        return panniers = servPannier.AllPannier();
    }

    public void setPanniers(List<Pannier> panniers) {
        this.panniers = panniers;
    }

    public Pannier getPan() {
        return pan;
    }

    public void setPan(Pannier pan) {
        this.pan = pan;
    }

    public int getMontant() throws ServiceException {
        List<Pannier> lp = servPannier.AllPannier();
        return montant = servPannier.ValeurPannier(lp);
    }

    public sVente getServVente() {
        return servVente;
    }

    public void setServVente(sVente servVente) {
        this.servVente = servVente;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    JasperPrint jasperPrint;

    public Vente getV() {
        return v;
    }

    public void setV(Vente v) {
        this.v = v;
    }

    public void init() throws JRException, ServiceException {
//          v=new Vente();
        panniers = servPannier.Facture();
//        for (Pannier pannier : panniers) {
//            v.setQuantite(pannier.getQuantite());
//           servVente.create(v, pannier.getProduit().getCode());
//        }



        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(panniers);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapports/facture.jasper");
        jasperPrint = JasperFillManager.fillReport(path, new HashMap(), beanCollectionDataSource);
    }

    public void pdf() throws JRException, IOException, ServiceException {

        init();

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("content-disposition", "attachment;filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
     
    }
}
