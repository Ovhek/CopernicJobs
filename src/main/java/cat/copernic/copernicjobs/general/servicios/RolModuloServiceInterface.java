/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;

/**
 *
 * @author Cole
 */
public interface RolModuloServiceInterface {

    public List<Modulo> findModulosByRolNom(String nom);

    public List<Rol> findRoleslByModuloNom(String nombre);
}
