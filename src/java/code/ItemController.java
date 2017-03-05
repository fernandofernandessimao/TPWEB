package code;

import code.util.JsfUtil;
import code.util.PaginationHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named("itemController")
@SessionScoped
public class ItemController implements Serializable {

    private DataModel items = null;
    @EJB
    private code.TItemFacade ejbFacade;
    @EJB
    private ItemFacadeLocal iFacade;
    @EJB
    private UtilizadorFacadeLocal uFacade;
    @EJB
    private MensagemFacadeLocal mFacade;
    @EJB
    private CancelamentoFacadeLocal cFacade;
    @EJB
    private NewsletterFacadeLocal nFacade;
    private PaginationHelper pagination;
    int id;
    String mensagem;
    float licitacao;
    String razao;
    int por;
    int findId = 0;
    private int NumRes = 0;
    private List<TItem> result = new ArrayList<>();

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public float getLicitacao() {
        return licitacao;
    }

    public void setLicitacao(float licitacao) {
        this.licitacao = licitacao;
    }

    public int getFindId() {
        return findId;
    }

    public void setFindId(int findId) {
        this.findId = findId;
    }

    public int getNumRes() {
        return NumRes;
    }

    public void setNumRes(int NumRes) {
        this.NumRes = NumRes;
    }

    public List<TItem> getResult() {
        return result;
    }

    public void setResult(List<TItem> result) {
        this.result = result;
    }

    public int getPor() {
        return por;
    }

    public void setPor(int por) {
        this.por = por;
    }
    
    private TItemFacade getFacade() {
        return ejbFacade;
    }

    public void calculaIds() {
        NumRes = 0;
        result.clear();
        List<TItem> todas = iFacade.getAll();
        if (findId <= 0) {
            return;
        }
        //result = todas;
        for (int j = 0; j < todas.size(); j++) {
            if (todas.get(j).getId() == findId) {
                result.add(todas.get(j));
            }
        }
        NumRes = result.size();
    }
    
    public void getItensPorCategoria() {
        NumRes = 0;
        result.clear();
        List<TItem> todas;
        todas = iFacade.ListByCategoria();
                
        for (int j = 0; j < todas.size(); j++) {
            result.add(todas.get(j));
        }
        NumRes = result.size();
    }
    
    public void getItensPorDescricao() {
        NumRes = 0;
        result.clear();
        List<TItem> todas;
        todas = iFacade.ListByDescricao();
                
        for (int j = 0; j < todas.size(); j++) {
            result.add(todas.get(j));
        }
        NumRes = result.size();
    }
    
    public void getItensPorPreco() {
        NumRes = 0;
        result.clear();
        List<TItem> todas;
        todas = iFacade.ListByValor();
                
        for (int j = 0; j < todas.size(); j++) {
            result.add(todas.get(j));
        }
        NumRes = result.size();
    }
    
    public void getItensPorPrazo() {
        NumRes = 0;
        result.clear();
        List<TItem> todas;
        todas = iFacade.ListByPrazo();
                
        for (int j = 0; j < todas.size(); j++) {
            result.add(todas.get(j));
        }
        NumRes = result.size();
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public List<TItem> getAll() {
        return iFacade.getAll();
    }

    public TItem getItem() {
        List<TItem> l = getAll();
        for (TItem i : l) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public String seguir(String username) {
        TUtilizador user = getUser(username);
        if (user == null) {
            return null;
        }
        
        TItem item = getItem();
        if (item == null) {
            return null;
        }

        Collection<TItem> it = user.getTItemSegueCollection();
        Iterator<TItem> iter = it.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iFacade.desseguir(user, getItem());
            }
        }
        iFacade.seguir(user, getItem());
        return "menuCliente";
    }

    public String licitacar(String username) {
        TUtilizador user = getUser(username);
        if (user == null) {
            return null;
        }

        TItem item = getItem();
        if (item == null) {
            return null;
        }

        if (!item.getConcluido() && licitacao > item.getValor() && user.getSaldo() >= licitacao && licitacao >= item.getPrecoInicial()) {
            iFacade.licitar(user, item, licitacao);
            if (licitacao >= item.getPrecoImediato()) {
                iFacade.setConcluido(item);
                iFacade.setComprador(user, item);
            }
        }
        return "menuCliente";
    }

    public String comprar(String username) {
        FacesContext context = FacesContext.getCurrentInstance();

        TUtilizador user = getUser(username);
        if (user == null) {
            return null;
        }
        if (getItem().getConcluido() && !getItem().getComprado()
                && getItem().getValor() <= user.getSaldo()
                && user.getUsername().equals(getItem().getCompradorid().getUsername())) {
            iFacade.setComprado(getItem());
            uFacade.increaseBalance(user, user.getSaldo()-getItem().getValor());
            nFacade.addNewsLetter("Compra Concluida", new Date(), "O utilizador " + user.getUsername() + " concluio a compra do item com id " + getItem().getId());
            return "menuCliente";
        }
        return null;
    }

    public String vendasRecentes() {
        FacesContext context = FacesContext.getCurrentInstance();
        String vr = "";
        TItem vra[] = new TItem[3];
        vra[0] = vra[1] = vra[2] = null;
        List<TItem> it = iFacade.getAll();

        for (int i = 0; i < it.size(); i++) {
            TItem item = it.get(i);
            if (item.getComprado()) {
                if (vra[0] == null || item.getUltLicData().after(vra[0].getUltLicData())) {
                    vra[2] = vra[1];
                    vra[1] = vra[0];
                    vra[0] = item;
                }
            }
        }

        if (vra[0] != null) {
            vr = "Descrição:" + vra[0].getDescricao() + "\nValor:" + vra[0].getValor() + "\n";
        }
        if (vra[1] != null) {
            vr += "Descrição:" + vra[1].getDescricao() + "\nValor:" + vra[1].getValor() + "\n";
        }
        if (vra[2] != null) {
            vr += "Descrição:" + vra[2].getDescricao() + "\nValor:" + vra[2].getValor() + "\n";
        }

        return vr;
    }

    public String cancelar() {
        if (!getItem().getConcluido() && !razao.isEmpty()) {
            cFacade.cancelar(getItem(), razao);
            nFacade.addNewsLetter("Item Cancelado", new Date(), "O leilão do item com id " + getItem().getId() + " foi cancelado");
            return "menuAdmin";
        }
        return null;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public TUtilizador getUser(String username) {
        List<TUtilizador> l = uFacade.getAll();
        for (TUtilizador u : l) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public TItem getTItem(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TItem.class)
    public static class TItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemController controller = (ItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemController");
            return controller.getTItem(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TItem) {
                TItem o = (TItem) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TItem.class.getName());
            }
        }

    }
}
