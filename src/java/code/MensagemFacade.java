package code;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    public List<TMensagem> getAllMensagens() {
        return dao.getEntityManager().createNamedQuery("TMensagem.findAll").
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
