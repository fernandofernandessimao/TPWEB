package code;

import javax.ejb.Local;

@Local
public interface CancelamentoFacadeLocal {

    void cancelar(TItem item, String razao);
    
}
