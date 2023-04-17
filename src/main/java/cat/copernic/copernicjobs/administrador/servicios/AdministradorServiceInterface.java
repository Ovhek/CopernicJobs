/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.model.Administrador;
import java.util.List;

/**
 *
 * @author joang
 */
public interface AdministradorServiceInterface {
    
    /**
     * Función para listar administradores en la base de datos.
     * @return  Lista de administradores.
     */
    public List<Administrador> listarAdministradors();
    
    /**
     * Función para añadir un administrador.
     * @param administrador objeto administrador a añadir.
     */
    public void anadirAdministrador(Administrador administrador); 
    
    /**
     * Función para eliminar un administrador.
     * @param administrador objeto administrador a eliminar.
     */
    public void eliminarAdministrador(Administrador administrador); 
    
    /**
     * Función que busca un objeto administrador en la base de datos.
     * @param administrador objeto administrador a buscar.
     * @return el objeto administrador si exist o null si no existe.
     */
    public Administrador buscarAdministrador(Administrador administrador); 

    /**
     * Función que busca un objeto administrador en la base de datos.
     * @param username username a buscar
     * @return el objeto administrador si existe o null si no existe.
     */
    public Administrador buscarAdministradorPorUsername(String username);
}
