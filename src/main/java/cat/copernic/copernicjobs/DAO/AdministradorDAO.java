/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.DAO;

import cat.copernic.copernicjobs.model.Administrador;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author joang
 */
public interface AdministradorDAO extends CrudRepository<Administrador,Integer>{
    
}
