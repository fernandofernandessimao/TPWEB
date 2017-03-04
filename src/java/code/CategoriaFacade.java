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
public class CategoriaFacade implements CategoriaFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TCategoria> getAll() {
        return dao.getEntityManager().createNamedQuery("TCategoria.findAll").getResultList();
    }

    @Override
    public void addCategoria(String categoria) {
        TCategoria c = new TCategoria();
        c.setNome(categoria);
        dao.getEntityManager().persist(c);
    }

    @Override
    public void changeCategoria(TCategoria categoria, String novaCategoria) {
        dao.getEntityManager().createNativeQuery("UPDATE t_categoria SET nome='" + novaCategoria + "' "
                + "WHERE nome='" + categoria.getId() + "';").executeUpdate();
    }
    
    @Override
    public TCategoria getCategoria(String nome) {
        return (TCategoria) dao.getEntityManager().createNamedQuery("TCategoria.findByNome")
                .setParameter("nome", nome)
                .getSingleResult();
    }

}
