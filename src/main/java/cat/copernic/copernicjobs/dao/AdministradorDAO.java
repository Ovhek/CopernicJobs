/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Administrador;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author joang
 */
public interface AdministradorDAO extends CrudRepository<Administrador,Integer>{
     /**
     * Obtener los usuarios basandonos en su rol
     * 1 --> alumne
     * 2 --> administrador
     * 3 --> empresa
     * @param rolId ID del rol.
     * @return Lista de usuarios.
     */
    List<Administrador> findByRol(int rolId);
}
