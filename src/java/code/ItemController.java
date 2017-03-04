package code;

import code.util.JsfUtil;
import code.util.PaginationHelper;
import java.io.Serializable;
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
    
    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();

        
    }
    
    public void enviarMensagem(String username) {
        FacesContext context = FacesContext.getCurrentInstance();

        TUtilizador user = getUser(username);
        
        TMensagem m = new TMensagem();
        m.setItemid(getItem());
        m.setMensagem(mensagem);
        m.setSenderid(user);
        m.setReceptorid(getItem().getVendedorid());
        m.setLida(false);
        mFacade.sendMessageByItem(m);
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
