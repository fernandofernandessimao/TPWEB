/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "utilizadorController")
@SessionScoped
public class UtilizadorController implements Serializable {

    @EJB
    private UtilizadorFacadeLocal uFacade;
    @EJB
    private NewsletterFacadeLocal nFacade;

    final List<TUtilizador> usersOnline = new ArrayList<>();

    String username;
    String nome;
    String morada;
    String password;
    float saldo;
    boolean active;
    boolean connected;
    final String PA = "Pedido de Adesao";
    final String AA = "Adesao Aceita";
    final String AN = "Adesao Negada";
    final String CS = "Conta Suspensa";
    final String CR = "Conta Reativada";
    final String IA = "Item a Venda";
    final String IV = "Item Vendido";
    final String IO = "Item Ofertado";
    final String IC = "Item Cancelado";
    final String IE = "Item Expirado";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String createNew() {
        uFacade.createNew(nome, morada, username, password);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        nFacade.addNewsLetter(PA, dateFormat.format(date), "Pedido de Adesão efetuado por " + nome);

        return "index";
    }

    public TUtilizador getUser() {
        List<TUtilizador> l = getAll();
        for (TUtilizador u : l) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
//        Para o caso de se enviarem por parâmetros de forms
//        Map params = context.getExternalContext().getRequestParameterMap();
//        String user = (String) params.get("user");
//        String pass = (String) params.get("pass");

        TUtilizador u = getUser();

        if ((u == null) || (u.getPassword().compareTo(password) != 0)) {
            context.addMessage(null, new FacesMessage("Nome de usuário/senha inválidos"));
            return null;
        }
        if (!u.getActivo()) {
            context.addMessage(null, new FacesMessage("Conta aguardando parecer do Administrador"));
            return null;
        }
        if (u.getConectado()) {
            context.addMessage(null, new FacesMessage("Usuário já está conectado"));
            return null;
        }
        if (u.getPassword().compareTo(password) != 0) {
            return null;
        }

        u.setConectado(true);
        usersOnline.add(u);

        if (u.getUsername().compareTo("admin") == 0) {
            return "menuAdmin";
        }
        return "menuCliente";
    }

    public void checkConnected(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        TUtilizador u = getUser();
        String texto = valor.toString();
        if (u.getConectado()) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuário já está conectado",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void checkDoppel(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        List<TUtilizador> l = getAll();
        String texto = valor.toString();
        l.stream().filter((u) -> (u.getUsername().equals(texto))).map((_item) -> new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Nome de usuário já registrado",
                "(" + texto + ")")).forEachOrdered((fmsg) -> {
            throw new ValidatorException(fmsg);
        });
    }

    public List<TUtilizador> getAll() {
        return uFacade.getAll();
    }

    public boolean getUserOnline() {
        for (TUtilizador u : usersOnline) {
            if (u.getUsername().compareTo(username) != 0) {
                return false;
            }
        }
        return true;
    }

    public void validaUsername(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Preencha o nome de usuário",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void validaNome(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Preencha o nome",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void validaMorada(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Preencha a morada",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void validaPassword(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Preencha a senha",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

}
