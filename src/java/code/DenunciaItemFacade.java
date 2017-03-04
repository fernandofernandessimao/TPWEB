package code;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DenunciaItemFacade implements DenunciaItemFacadeLocal {
    
    @EJB
    private DAOLocal dao;

    @Override
    public void createDenuncia(TItem item, String razao, Date data) {
        TDenunciaItem d = new TDenunciaItem();
        d.setIditem(item);
        d.setRazao(razao);
        d.setDataDen(data);
        dao.getEntityManager().persist(d);
    }
}
