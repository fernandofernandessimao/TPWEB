/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fernando
 */
@Local
public interface SuspensaoFacadeLocal {

    List<TSuspensao> getAll();

    void addSuspension(TUtilizador user, String reason);

}
