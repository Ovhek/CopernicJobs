/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.RolModulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * DAO de la tabla RolModulo
 *
 * @author joang
 */
public interface RolModuloDAO extends JpaRepository<RolModulo, Integer> {

    /**
     *
     * Busca los módulos asociados a un rol por su nombre.
     *
     * @param nombreRol el nombre del rol.
     * @return una lista de los módulos asociados al rol.
     */
    @Query("SELECT rm.modulo FROM RolModulo rm WHERE rm.rol.nom = :nombreRol")
    List<Modulo> findModulosByNombreRol(@Param("nombreRol") String nombreRol);

    /**
     *
     * Devuelve una lista de roles que tienen acceso al módulo con el nombre
     * especificado.
     *
     * @param nombreModulo El nombre del módulo.
     * @return Una lista de roles que tienen acceso al módulo con el nombre
     * especificado.
     */
    @Query("SELECT rm.rol FROM RolModulo rm WHERE rm.modulo.nombre = :nombreModulo")
    List<Rol> findRolesByNombreModulo(@Param("nombreModulo") String nombreModulo);
}
