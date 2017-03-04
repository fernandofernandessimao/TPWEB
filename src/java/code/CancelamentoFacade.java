package code;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CancelamentoFacade implements CancelamentoFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public void cancelar(TItem item, String razao) {
        TCancelamento c = new TCancelamento();
        c.setItemid(item);
        c.setRazao(razao);
        c.setDataCanc(new Date());
        dao.getEntityManager().persist(c);
        dao.getEntityManager().createNativeQuery("UPDATE t_item SET concluido='" + true + "'"
                + "WHERE id='" + item.getId() + "';").executeUpdate();
    }
}
