package code;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class BackgroundJobManager {

    @EJB
    private ItemFacadeLocal iFacade;
    @EJB
    private UtilizadorFacadeLocal uFacade;

   public TUtilizador getUser(String username) {
        List<TUtilizador> l = uFacade.getAll();
        for (TUtilizador u : l) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    @Schedule(hour="0", minute="0", second="0", persistent=false)
    public void DailyJob() {
        Date d = new Date();
        List<TItem> l = iFacade.getAll();
        for(int i=0;i<l.size();i++){
            if (!d.before(l.get(i).getPrazo()) && !l.get(i).getConcluido()) {
                iFacade.setConcluido(l.get(i));
                if(l.get(i).getBidder()!=null)
                    iFacade.setComprador(getUser(l.get(i).getBidder()), l.get(i));
            }
        }
    }
}
