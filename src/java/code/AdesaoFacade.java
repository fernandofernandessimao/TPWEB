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
public class AdesaoFacade implements AdesaoFacadeLocal {
    
    @EJB
    private DAOLocal dao;
    
    @Override
    public List<TAdesao> getAll() {        
        return dao.getEntityManager().createNamedQuery("TAdesao.findAll").getResultList();
    }
    
    @Override
    public void addPedidoAdesao(String nome, String morada, String username, String password) {
        TAdesao a = new TAdesao();
        a.setNome(nome);
        a.setMorada(morada);
        a.setUsername(username);
        a.setPassword(password);
        a.setPendente(true);
        dao.getEntityManager().persist(a);
    }
    
}
