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
public interface AdesaoFacadeLocal {

    List<TAdesao> getAll();

    void addPedidoAdesao(String nome, String morada, String username, String password);

}
