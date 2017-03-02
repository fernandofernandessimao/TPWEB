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
public class SuspensaoFacade implements SuspensaoFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TSuspensao> getAll() {
        return dao.getEntityManager().createNamedQuery("TSuspensao.findAll").getResultList();
    }

    @Override
    public void addSuspension(TUtilizador user, String reason) {
        TSuspensao s = new TSuspensao();
        s.setUtilizadorid(user);
        s.setRazao(reason);
        s.setPendente(false);
        dao.getEntityManager().persist(s);
    }

}
