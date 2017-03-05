package code;

import java.util.Date;
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
        user.getTItemSegueCollection().add(item);
        item.getTUtilizadorSegueCollection().add(user);
        dao.getEntityManager().merge(user);
        dao.getEntityManager().merge(item);
    }

    @Override
    public void desseguir(TUtilizador user, TItem item) {
        user.getTItemSegueCollection().remove(item);
        item.getTUtilizadorSegueCollection().remove(user);
        dao.getEntityManager().merge(user);
        dao.getEntityManager().merge(item);
    }

    @Override
    public void licitar(TUtilizador user, TItem item, float valor) {
        TLicitacao tl = new TLicitacao();
        tl.setItemid(item);
        tl.setUtilizadorid(user);
        tl.setData(new Date());
        tl.setValor(valor);
        dao.getEntityManager().persist(tl);
        
        item.setValor(valor);
        item.setBidder(user.getUsername());
        dao.getEntityManager().persist(item);
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

    @Override
    public List<TItem> getPersonalItens(TUtilizador user) {
        return dao.getEntityManager().createNamedQuery("TItem.findByUser")
                .setParameter("vendedorid", user).setParameter("concluido", false)
                .getResultList();
    }

    @Override
    public List<TItem> getItensByID(int id) {
        return dao.getEntityManager().createNamedQuery("TItem.findItemsByID")
                .setParameter("id", id)
                .getResultList();
    }
}
