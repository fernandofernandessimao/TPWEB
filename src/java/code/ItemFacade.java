package code;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ItemFacade implements ItemFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TItem> getAll() {
        return dao.getEntityManager().
                createNamedQuery("TItem.findAll").
                getResultList();
    } 
    
    @Override
    public List<TItem> ListByCategoria() {
        return dao.getEntityManager().createNamedQuery("SELECT t FROM TItem t ORDER BY t.categoriaid.nome").getResultList();
    }

    @Override
    public List<TItem> ListByDescricao() {
        return dao.getEntityManager().createNamedQuery("SELECT t FROM TItem t ORDER BY t.descricao").getResultList();
    }

    @Override
    public List<TItem> ListByValor() {
        return dao.getEntityManager().createNamedQuery("SELECT t FROM TItem t ORDER BY t.valor").getResultList();
    }

    @Override
    public List<TItem> ListByPrazo() {
        return dao.getEntityManager().createNamedQuery("SELECT t FROM TItem t ORDER BY t.prazo").getResultList();
    }
    
    @Override
    public void addItem(TItem item) {
        dao.getEntityManager().persist(item);
    }

    @Override
    public void seguir(TUtilizador user, TItem item) {
        dao.getEntityManager().createNativeQuery("INSERT INTO t_segue (utilizadorid, itemid) VALUES (?,?)").setParameter(1, user).setParameter(2, item).executeUpdate();
    }

    @Override
    public void desseguir(TUtilizador user, TItem item) {
        dao.getEntityManager().createNativeQuery("DELETE FROM t_segue WHERE utilizadorid='" + user + "' AND itemid='" + item + "'").executeUpdate();
    }

    @Override
    public void licitar(TUtilizador user, TItem item, float valor) {
        dao.getEntityManager().createNativeQuery("INSERT INTO t_licitacao (utilizadorid, itemid) VALUES (?,?)").setParameter(1, user).setParameter(2, item).executeUpdate();
    }

    @Override
    public void setConcluido(TItem item) {
        dao.getEntityManager().createNativeQuery("UPDATE t_item SET concluido='" + true + "' "
                + "WHERE id='" + item.getId() + "';").executeUpdate();
    }
    
    @Override
    public void setComprado(TItem item) {
        dao.getEntityManager().createNativeQuery("UPDATE t_item SET comprado='" + true + "' "
                + "WHERE id='" + item.getId() + "';").executeUpdate();
    }
}
