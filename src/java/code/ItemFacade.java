/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Fernando
 */
@Stateless
public class ItemFacade implements ItemFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TItem> getAll() {
        return dao.getEntityManager().createNamedQuery("TItem.findAll").getResultList();
    }

    @Override
    public TItem getItem() {
       return null;
    }

    @Override
    public void addItem(TItem item) {
        dao.getEntityManager().persist(item);
    }

}
