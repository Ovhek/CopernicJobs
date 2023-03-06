/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Incidencia;
import java.util.List;

/**
 *
 * @author Cole
 */
public interface IncidenciaServiceInterface {
        
    /**
     * Función para listar incidencias en la base de datos.
     * @return  Lista de incidencias.
     */
    public List<Incidencia> listarIncidencias();
    
    /**
     * Función para añadir una incidencia..
     * @param incidencia objeto incidencia a añadir.
     */
    public void anadirIncidencia(Incidencia incidencia); 
    
    /**
     * Función para eliminar una incidencia.
     * @param incidencia objeto incidencia a eliminar.
     */
    public void eliminarIncidencia(Incidencia incidencia); 
    
    /**
     * Función que busca un objeto alumno en la base de datos.
     * @param incidencia objeto incidencia a buscar.
     * @return el objeto incidencia si exist o null si no existe.
     */
    public Incidencia buscarIncidencia(Incidencia incidencia); 
}
