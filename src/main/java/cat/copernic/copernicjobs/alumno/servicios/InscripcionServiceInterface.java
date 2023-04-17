/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.servicios;

import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Inscripcion;
import java.util.List;

/**
 *  Interfaz para el servicio de la inscripcion.
 * @author Alex
 */
public interface InscripcionServiceInterface {
    
    /**
     * Función para listar alumnos en la base de datos.
     * @return  Lista de inscripciones.
     */
    public List<Inscripcion> listarInscripciones();
    
    /**
     * Función para añadir una inscripcion.
     * @param inscripcion  objeto inscripcion a añadir.
     */
    public void anadirInscripcion(Inscripcion inscripcion); 
    
    /**
     * Función para eliminar un inscripcion.
     * @param inscripcion  objeto inscripcion a eliminar.
     */
    public void eliminarInscripcion(Inscripcion inscripcion); 
    
    /**
     * Función que busca un objeto inscripcion en la base de datos.
     * @param inscripcion  objeto inscripcion a buscar.
     * @return el objeto inscripcion si exist o null si no existe.
     */
    public Inscripcion buscarInscripcion(Inscripcion inscripcion); 
    
    /**
     * Busca una lista de inscripciones basandose en el id del alumno
     * @param id id del alumno
     * @return Lista de inscripciones
     */
    public List<Inscripcion> buscarInscripcionPorAlumnoId(int id);
    
     /**
     * Busca una lista de inscripciones basandose en el id de la empresa.
     * @param id id de la empresa
     * @return Lista de inscripciones
     */
    public List<Inscripcion> buscarInscripcionPorOfertaId(int id);
   
    
}
