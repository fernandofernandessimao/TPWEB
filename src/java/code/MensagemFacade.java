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
public class MensagemFacade implements MensagemFacadeLocal {

    @EJB
    private DAOLocal dao;

    @Override
    public List<TMensagem> getAll(TUtilizador user) {
        return dao.getEntityManager().createNamedQuery("TMensagem.findBySender").
                setParameter("senderid", user.getUsername()).
                getResultList();
    }

    @Override
    public void sendMessage(TMensagem m) {
        m.setLida(false);
        dao.getEntityManager().persist(m);
    }
    
    @Override
    public void sendMessageByItem(TMensagem m) {
        dao.getEntityManager().persist(m);
    }
}
