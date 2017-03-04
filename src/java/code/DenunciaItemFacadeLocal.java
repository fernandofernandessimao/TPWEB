/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author João
 */
@Local
public interface DenunciaItemFacadeLocal {

    void createDenuncia(TItem item, String razao, Date data);
    
}
