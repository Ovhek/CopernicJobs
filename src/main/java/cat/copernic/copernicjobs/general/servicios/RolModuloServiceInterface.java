/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;

/**
 * Interfaz para el RolModulo
 *
 * @author Alex
 */
public interface RolModuloServiceInterface {

    /**
     *
     * Método que busca los módulos asociados a un rol con un nombre específico.
     *
     * @param nom El nombre del rol a buscar.
     * @return La lista de módulos asociados al rol.
     */
    public List<Modulo> findModulosByRolNom(String nom);

    /**
     *
     * Método que busca los roles asociados a un módulo con un nombre
     * específico.
     *
     * @param nombre El nombre del módulo a buscar.
     * @return La lista de roles asociados al módulo.
     */
    public List<Rol> findRoleslByModuloNom(String nombre);
}
