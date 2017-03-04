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
    
    
}
