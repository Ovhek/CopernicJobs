/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO de la entidad modulo.
 * @author joang
 */
public interface ModuloDAO extends JpaRepository<Modulo,Integer>{
    
}
