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
public class ReativacaoFacade implements ReativacaoFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TReactivacao> getAll() {
        return dao.getEntityManager().createNamedQuery("TReactivacao.findAll").getResultList();
    }

    @Override
    public void addReativacao(TUtilizador user) {
        TReactivacao r = new TReactivacao();
        r.setUtilizadorid(user);
        r.setPendente(true);
        dao.getEntityManager().persist(r);
    }

}
