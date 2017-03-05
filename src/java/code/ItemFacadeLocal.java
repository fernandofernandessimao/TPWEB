package code;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ItemFacadeLocal {

    List<TItem> getAll();

    List<TItem> getItensByID(int id);
    
    List<TItem> ListByCategoria();

    List<TItem> ListByDescricao();

    List<TItem> ListByValor();

    List<TItem> ListByPrazo();
    
    List<TItem> getPersonalItens(TUtilizador user);
    
    public void addItem(TItem item);
    
    void seguir(TUtilizador user, TItem item);

    void desseguir(TUtilizador user, TItem item);

    void licitar(TUtilizador user, TItem item, float valor);

    void setConcluido(TItem item);

    void setComprado(TItem item);
    
    void setComprador(TUtilizador user, TItem item);
}
