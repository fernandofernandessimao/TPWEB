package code;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ItemFacadeLocal {

    List<TItem> getAll();

}
