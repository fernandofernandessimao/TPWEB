/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando
 */
@Local
public interface DAOLocal {

    EntityManager getEntityManager();
}
