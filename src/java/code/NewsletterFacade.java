/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Fernando
 */
@Stateless
public class NewsletterFacade implements NewsletterFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TNewsletter> getAll() {
        List t = dao.getEntityManager().createNamedQuery("TNewsletter.findAll").getResultList();
        Collections.reverse(t);
        return t;
    }

    @Override
    public void addNewsLetter(String tipo, Date data, String mensagem) {
        TNewsletter n = new TNewsletter(tipo, data, mensagem);
        dao.getEntityManager().persist(n);
    }

    @Override
    public int totalNewsLetter() {
        return getAll().size();
    }
}
