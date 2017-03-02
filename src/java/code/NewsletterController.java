/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named(value = "newsletterController")
@SessionScoped
public class NewsletterController implements Serializable {

    @EJB
    private NewsletterFacadeLocal nFacade;

    private int results = 0;

    public List<TNewsletter> getAll() {
        return nFacade.getAll();
    }

    public void addNewsLetter(String tipo, Date data, String mensagem) {
        nFacade.addNewsLetter(tipo, data, mensagem);
    }

    public int totalNewsletter() {
        return results = nFacade.totalNewsLetter();
    }

    public int getResults() {
        return results;
    }

}
