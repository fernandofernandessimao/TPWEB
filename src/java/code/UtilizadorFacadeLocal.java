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
public interface UtilizadorFacadeLocal {

    List<TUtilizador> getAll();

    void createNew(String username, String nome, String morada, String password);

    void changeName(TUtilizador user, String nome);
    
    void changeAdress(TUtilizador user, String address);
    
    void changePassword(TUtilizador user, String password);
    
    void increaseBalance(TUtilizador user, float valor);
    
    void suspensionRequest(TUtilizador user, String reason);
    
}
