/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.beans;

import com.lateu.commerce.entites.Stock;
import com.lateu.commerce.metier.ServiceException;
import com.lateu.commerce.metier.sStock;
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
@ManagedBean(name ="stock")
@RequestScoped
public class Stockbean implements Serializable {
    @ManagedProperty(value = "#{sStock}")
    private sStock servStock;
    private Stock stockselected = new Stock();
    public List<Stock> stocks;
    public Stockbean() {
    }

    public String updateStock() throws ServiceException {
        servStock.updateStock(stockselected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mise à jour du Stock reussie !!", " "));
        return "updatestock";
    }

    public String deleteStock() {


        return "deleteStock";
    }

    public sStock getServStock() {
        return servStock;
    }

    public void setServStock(sStock servStock) {
        this.servStock = servStock;
    }

    public Stock getStockselected() {
        return stockselected;
    }

    public void setStockselected(Stock stockselected) {
        this.stockselected = stockselected;
    }

    public List<Stock> getStocks() throws ServiceException {
        return stocks = servStock.findAll();
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
