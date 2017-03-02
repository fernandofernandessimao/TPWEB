/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fernando
 */
@Local
public interface NewsletterFacadeLocal {
    
    List<TNewsletter> getAll();    

    int totalNewsLetter();
    
    void addNewsLetter(String tipo, Date data, String mensagem);
}
