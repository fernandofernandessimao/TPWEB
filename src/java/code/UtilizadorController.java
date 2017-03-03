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
import java.util.regex.Pattern;
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
    @EJB
    private SuspensaoFacadeLocal sFacade;
    @EJB
    private ReativacaoFacadeLocal rFacade;
    @EJB
    private AdesaoFacadeLocal aFacade;

    final List<String> usersOnline = new ArrayList<>();

    List<TUtilizador> findUsers = new ArrayList<>();

    String username;
    String errorMsg;
    String nome;
    String morada;
    String password, newPassword = null, oldPassword = null;
    String razao;
    String findname = null;
    int usersFound = 0;
    float saldo, thisSaldo;
    float valor;
    boolean active;
    boolean connected;
    final String PA = "Pedido de Adesao";
    final String PR = "Pedido de Reativação";
    final String PS = "Pedido de Suspensão";
    final String AA = "Adesao Aceita";
    final String AN = "Adesao Negada";
    final String CS = "Conta Suspensa";
    final String CR = "Conta Reativada";
    final String IA = "Item a Venda";
    final String IV = "Item Vendido";
    final String IO = "Item Ofertado";
    final String IC = "Item Cancelado";
    final String IE = "Item Expirado";

    private int NumRes = 0;
    private List<TUtilizador> result = new ArrayList<>();

    ;
    public int getNumRes() {
        return NumRes;
    }

    public void calculaSomePessoas() {
        NumRes = 0;
        result.clear();
        List<TUtilizador> todas = uFacade.getAll();
        // Este método não está nada optimizado
        // O filtro deverá ser feito na pesquisa à base de dados
        // exemplo, uma named query findByName
        if ((findname == null) || (findname.length() == 0)) {
            return;
        }
        todas.stream().filter((p) -> (p.getUsername().contains(findname))).forEachOrdered((p) -> {
            result.add(p);
        });
        NumRes = result.size();
    }

    public List<TUtilizador> getSomePessoas() {
        return result;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public int getUserFound() {
        return usersFound;
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
        TUtilizador u = getUser();
        saldo = thisSaldo = u.getSaldo();
        return u.getSaldo();
    }

    public float getThisSaldo() {
        return thisSaldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public TSuspensao getSuspensao(TUtilizador user) {
        List<TSuspensao> l = sFacade.getAll();
        for (TSuspensao u : l) {
            if (u.getUtilizadorid().equals(user)) {
                return u;
            }
        }
        return null;
    }

    public String suspensionRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        TUtilizador u = getUser();

        if (getSuspensao(u) != null) {
            context.addMessage(null, new FacesMessage("Pedido de Suspensão já efetuado"));
            return null;
        }
        sFacade.addSuspension(u, razao);

        Date date = new Date();
        nFacade.addNewsLetter(PS, date, "Pedido de Suspensão efetuado por " + u.getNome());
        return "menuCliente";
    }

    public String createNew() {
        uFacade.createNew(nome, morada, username, password);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        nFacade.addNewsLetter(PA, dateFormat.format(date), "Pedido de Adesão efetuado por " + nome);

        return "index";
    }

    public String addPedidoAdesao() {
        aFacade.addPedidoAdesao(nome, morada, username, password);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        nFacade.addNewsLetter(PA, date, "Pedido de Adesão efetuado por " + nome);
        return "menuVisitante";
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

    public void findUsers() {

        usersFound = 0;
        findUsers.clear();

        if (findname == null || findname.isEmpty()) {
            return;

        }
        findUsers = uFacade.findUsers(findname);
        usersFound = findUsers.size();

    }

    public int getNumberUsers() {
        return findUsers.size();
    }

    public String getFindname() {
        return findname;
    }

    public void setFindname(String findname) {
        this.findname = findname;
    }

    public List<TUtilizador> getUsersFound() {
        return findUsers;
    }

    public String changeName() {
        uFacade.changeName(getUser(), nome);
        // FacesMessage message = new FacesMessage(null, "Operação efetuada com sucesso.");
        // RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "GerirCliente";

    }

    public String changeAddress() {
        uFacade.changeAdress(getUser(), morada);
        return "GerirCliente";
    }

    public void changePassword() {
        uFacade.changePassword(getUser(), newPassword);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password alterada com sucesso", "Password alterada com sucesso");
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
        //return getUser().getUsername().equals("admin") ? "menuAdmin" : "GerirCliente";
    }

    public String increaseBalance() {
        uFacade.increaseBalance(getUser(), valor + getSaldo());
        TUtilizador u = getUser();
        saldo = u.getSaldo();
        return "menuCliente";
    }

    public String reativacao() {
        FacesContext context = FacesContext.getCurrentInstance();
        TUtilizador u = getUser();

        if ((u == null) || (u.getPassword().compareTo(password) != 0)) {
            context.addMessage(null, new FacesMessage("Nome de usuário/senha inválidos"));
            return null;
        }
        if (u.getActivo()) {
            context.addMessage(null, new FacesMessage("A conta ainda está ativa."));
            return null;
        }
        if (u.getConectado()) {
            context.addMessage(null, new FacesMessage("A conta ainda não foi aprovada pelo administrador."));
            return null;
        }

        Date date = new Date();
        nFacade.addNewsLetter(PR, date, "Pedido de Reativação efetuado por " + username);

        rFacade.addReativacao(u);

        return "menuVisitante";
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
            context.addMessage(null, new FacesMessage("Conta suspensa."));
            return null;
        }
//        if (checkConnected()) {
//            context.addMessage(null, new FacesMessage("Usuário já está conectado"));
//            return null;
//        }
        if (u.getPassword().compareTo(password) != 0) {
            return null;
        }

//        if (u.getConectado() == true) {
//            context.addMessage(null, new FacesMessage("O utilizador já está conetado."));
//            return null;
//        }
        uFacade.changeConectado(u, true);

        //saldo = u.getSaldo();
        // usersOnline.add(u.getUsername());
        // resetFields();
        if (u.getUsername().compareTo("admin") == 0) {
            return "/admin/menuAdmin";
        }

        return "/user/menuCliente";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();

        TUtilizador u = getUser();

        uFacade.changeConectado(u, true);
        return "/menuVisitante";
    }

    public void resetFields() {
        password = morada = nome = null;
    }

    public boolean checkConnected() {
        return usersOnline.stream().anyMatch((s) -> (s.compareTo(username) == 0));
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
        return usersOnline.stream().noneMatch((u) -> (u.compareTo(username) != 0));
    }

//    public void showMessage() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        FacesMessage message = new FacesMessage(null, "Operação efetuada com sucesso.");
//        
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//    }
    public void validaUsername(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if (texto.length() > 30) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Morada excedeu o tamanho máximo (30)",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Preencha o nome de usuário",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
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

    public void validaNome(FacesContext fc, UIComponent uic, Object valor) {
        String texto = valor.toString();
        if (texto.length() > 50) {
            errorMsg = "Nome excedeu o tamanho máximo (50)";
        }
        if ((texto.length() < 1) || texto.matches("[0-9]+")) {
            errorMsg = "Preencha o nome";
        }
    }

    public void validaMorada(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if (texto.length() > 50) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Morada excedeu o tamanho máximo (50)",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
        if ((texto.length() < 1)) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Preencha a morada",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void validaSaldo(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        Pattern p = Pattern.compile("[0-9]");
        // System.out.println(texto);
        if (texto.length() > 5) {
            // System.out.println("1");
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Incremento máximo de 999",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
        if (!p.matcher(texto).find()) {
            // System.out.println("2");
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Insira o valor",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
        if (Float.valueOf(texto) < 50) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Valor mínimo do incremento é 50",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

    public void checkPassword(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if (!texto.equals(getUser().getPassword())) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Senha inválida",
                    "Senha inválida");
            throw new ValidatorException(fmsg);
        }
//        if ((texto.length() < 1)) {
//            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Preencha a senha",
//                    "(" + texto + ")");
//            throw new ValidatorException(fmsg);
//        }
    }

    public void validaPassword(FacesContext fc, UIComponent uic, Object valor) throws ValidatorException {
        String texto = valor.toString();
        if (texto.length() > 30) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Excedeu o tamanho máximo (30)",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
        if ((texto.length() < 1)) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Preencha a senha",
                    "(" + texto + ")");
            throw new ValidatorException(fmsg);
        }
    }

}
