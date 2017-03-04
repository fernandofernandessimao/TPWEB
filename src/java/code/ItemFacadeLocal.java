package code;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ItemFacadeLocal {

    List<TItem> getAll();

    List<TItem> ListByCategoria();

    List<TItem> ListByDescricao();

    List<TItem> ListByValor();

    List<TItem> ListByPrazo();
    
    void seguir(TUtilizador user, TItem item);

    void desseguir(TUtilizador user, TItem item);

    void licitar(TUtilizador user, TItem item, float valor);

    void setConcluido(TItem item);

    void setComprado(TItem item);
}
