/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Fernando
 */
@Named(value = "adesaoController")
@SessionScoped
public class AdesaoController implements Serializable {

    @EJB
    private AdesaoFacadeLocal aFacade;

    public List<TAdesao> getAll() {
        return aFacade.getAll();
    }
}
