/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named(value = "suspensaoController")
@SessionScoped
public class SuspensaoController implements Serializable {

    @EJB
    private SuspensaoFacadeLocal sFacade;

    String reason;

    public String getReason() {
        return reason;
    }

    public List<TSuspensao> getAll() {
        return sFacade.getAll();
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void validaRazao(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if (texto.length() < 20 || texto.length() > 200) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Tamanho mínimo/máximo 20/200 caracteres",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

}
