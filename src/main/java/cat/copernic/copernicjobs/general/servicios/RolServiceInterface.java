/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;

/**
 * Interfaz para el servicio de Rol.
 * @author Alex
 */
public interface RolServiceInterface {
        
    /**
     * Función para listar roles en la base de datos.
     * @return  Lista de roles.
     */
    public List<Rol> listarRoles();
    
    /**
     * Función para añadir un rol..
     * @param rol objeto incidencia a añadir.
     */
    public void anadirRol(Rol rol); 
    
    /**
     * Función para eliminar un rol.
     * @param rol objeto incidencia a eliminar.
     */
    public void eliminarRol(Rol rol); 
    
    /**
     * Función que busca un objeto rol en la base de datos.
     * @param rol objeto rol a buscar.
     * @return el objeto rol si exist o null si no existe.
     */
    public Rol buscarRol(Rol rol); 
}
