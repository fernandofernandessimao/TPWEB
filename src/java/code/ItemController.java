package code;

import code.util.JsfUtil;
import code.util.PaginationHelper;
import java.io.Serializable;
import java.util.Collection;
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
public class ItemController implements Serializable{
    private DataModel items = null;
    @EJB
    private code.TItemFacade ejbFacade;
    @EJB
    private ItemFacadeLocal iFacade;
    @EJB
    private UtilizadorFacadeLocal uFacade;
    @EJB
    private MensagemFacadeLocal mFacade;
    private PaginationHelper pagination;
    int id;
    String mensagem;
    float licitacao;

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
    
    private TItemFacade getFacade() {
        return ejbFacade;
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
            if (i.getId()==id) {
                return i;
            }
        }
        return null;
    }
    
    public void seguir(String username){
        TUtilizador user = getUser(username);
        if(user==null)
            return;
        
        Collection<TItem> it = user.getTItemSegueCollection();
        Iterator<TItem> iter = it.iterator();
        while(iter.hasNext()){
            if(iter.next().getId()==id){
                iFacade.seguir(user, getItem());
            }
        }
        iFacade.desseguir(user, getItem());
    }
    
    public void licitacar(String username){
        TUtilizador user = getUser(username);
        if(user==null)
            return;
        
        TItem item = getItem();
        if(item==null)
            return;
        
        if(!item.getConcluido() && licitacao>item.getValor() && user.getSaldo()>=licitacao){
            iFacade.licitar(user, item, licitacao);
            if(licitacao>=item.getPrecoImediato()){
                iFacade.setConcluido(item);
            }
        }
    }
    
    public void comprar(String username) {
        FacesContext context = FacesContext.getCurrentInstance();

        TUtilizador user = getUser(username);
        if(user==null){
            return;
        }
        if(getItem().getConcluido() && !getItem().getComprado() && 
                getItem().getValor() <= user.getSaldo() && 
                user.getUsername().equals(getItem().getCompradorid().getUsername())){
            iFacade.setComprado(getItem());
            uFacade.increaseBalance(user, -getItem().getValor());
        }
    }
    
    public String vendasRecentes() {
        FacesContext context = FacesContext.getCurrentInstance();
        String vr = "";
        TItem vra[] = new TItem[3];
        vra[0] = vra[1] = vra[2] = null;
        List<TItem> it= iFacade.getAll();
        
        for(int i=0;i<it.size();i++){
            TItem item = it.get(i);
            if(item.getComprado()){
                if(vra[0] == null || item.getUltLicData().after(vra[0].getUltLicData())){
                        vra[2] = vra[1];
                        vra[1] = vra[0];
                        vra[0] = item;
                }
            }
        }
        
        if(vra[0]!=null)
            vr = "Descrição:" + vra[0].getDescricao() + "\nValor:" + vra[0].getValor() + "\n";
        if(vra[1]!=null)    
            vr += "Descrição:" + vra[1].getDescricao() + "\nValor:" + vra[1].getValor() + "\n";
        if(vra[2]!=null)
            vr += "Descrição:" + vra[2].getDescricao() + "\nValor:" + vra[2].getValor() + "\n";
        
        return vr;
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
