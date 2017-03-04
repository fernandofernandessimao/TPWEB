package code;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TDenunciaUtilizadorFacade extends AbstractFacade<TDenunciaUtilizador> {

    @PersistenceContext(unitName = "TPWEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDenunciaUtilizadorFacade() {
        super(TDenunciaUtilizador.class);
    }
}
