package code;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TDenunciaItemFacade extends AbstractFacade<TDenunciaItem> {

    @PersistenceContext(unitName = "TPWEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDenunciaItemFacade() {
        super(TDenunciaItem.class);
    }
}
