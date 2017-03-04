package code;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DenunciaUtilizadorFacade implements DenunciaUtilizadorFacadeLocal {

    @EJB
    private DAOLocal dao;
    
    @Override
    public void createDenuncia(TUtilizador username, String razao, Date data) {
        TDenunciaUtilizador d = new TDenunciaUtilizador();
        d.setUsername(username);
        d.setRazao(razao);
        d.setDataDen(data);
        dao.getEntityManager().persist(d);
    }
}
