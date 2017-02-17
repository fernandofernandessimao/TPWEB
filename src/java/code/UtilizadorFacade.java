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
public class UtilizadorFacade implements UtilizadorFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TUtilizador> getAll() {
        return dao.getEntityManager().
                createNamedQuery("TUtilizador.findAll").
                getResultList();
    }

    @Override
    public void createNew(String nome, String morada, String username, String password) {
        TUtilizador u = new TUtilizador(username, nome, morada, password);
        dao.getEntityManager().persist(u);
    }
}
