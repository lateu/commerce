/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.statistiques;

import com.lateu.commerce.entites.Vente;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sVente;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class VariationVente implements Serializable {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
    private sVente servente = (sVente) ctx.getBean("sVente");
    private CartesianChartModel linearModel;
    public List<Vente> alimentaires;
    public List<Vente> electromenagers;

    public VariationVente() throws ServiceException {
        createLinearModel();
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }

    private void createLinearModel() throws ServiceException {
        linearModel = new CartesianChartModel();
        String[] month = {"janv", "fev", "mars", "avril", "mai", "juin", "juillet", "aout", "sept", "oct", "nov", "dec"};
        int[] code = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("produit alimentaire");

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("produit electromenager");
        series2.setMarkerStyle("diamond");


        alimentaires = servente.ventsproduit("alimentaire");
        electromenagers = servente.ventsproduit("electromenager");



        for (Vente v : alimentaires) {
            for (int i = 0; i < 12; i++) {

                if (v.getDateRecette().getMonth() == code[i]) {
                    series1.set(month[i], v.getQuantite());
                }
            }
        }


        for (Vente v2 : electromenagers) {
            for (int i = 0; i < 12; i++) {
                if (v2.getDateRecette().getMonth() == code[i]) {
                    series2.set(month[i], v2.getQuantite());
                }
            }

        }
        linearModel.addSeries(series1);
        linearModel.addSeries(series2);

    }
}
