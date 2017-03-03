package code;

import code.util.JsfUtil;
import code.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("tAdesaoController")
@SessionScoped
public class TAdesaoController implements Serializable {

    private TAdesao current;
    private DataModel items = null;
    @EJB
    private code.TAdesaoFacade ejbFacade;
    @EJB
    private NewsletterFacadeLocal nFacade;
    @EJB
    private UtilizadorFacadeLocal uFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;
    boolean pendente;
    boolean aceite;

 
    
    public boolean isPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public TAdesaoController() {
    }

    public TAdesao getSelected() {
        if (current == null) {
            current = new TAdesao();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TAdesaoFacade getFacade() {
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareEdit() {
        current = (TAdesao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "EditarAdesao";
    }

    public String update() {
        try {
            Date date = new Date();
            current.setDataProc(date);

            if (!current.getPendente() && current.getAceite()) {
                nFacade.addNewsLetter("Adesão Aceita", date, "Pedido de Adesão efetuado por " + current.getUsername() + "aceito.");
                uFacade.createNew(current.getUsername(), current.getNome(), current.getMorada(), current.getPassword());
            } else {
                nFacade.addNewsLetter("Adesão Negada", date, "Pedido de Adesão efetuado por " + current.getUsername() + " recusado. Motivo: " + current.getRazaoRej());
            }

            getFacade().edit(current);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TAdesaoUpdated"));
            return "ListarAdesao";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
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

    public TAdesao getTAdesao(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TAdesao.class)
    public static class TAdesaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TAdesaoController controller = (TAdesaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tAdesaoController");
            return controller.getTAdesao(getKey(value));
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
            if (object instanceof TAdesao) {
                TAdesao o = (TAdesao) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TAdesao.class.getName());
            }
        }

    }

}
