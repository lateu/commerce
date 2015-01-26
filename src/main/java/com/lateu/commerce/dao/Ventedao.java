/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.commerce.dao;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.IDao;
import com.lateu.commerce.entites.Vente;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Ventedao extends IDao<Vente, Long> {

    List<Vente> findByType(String type) throws DataAccessException;

    List<Vente> findrecetteJournaliere(Date d) throws DataAccessException;

    List<Vente> findrecettePeriodique(Date d1, Date d2) throws DataAccessException;

    public List<Vente> venteProduit(String type) throws DataAccessException;
}
