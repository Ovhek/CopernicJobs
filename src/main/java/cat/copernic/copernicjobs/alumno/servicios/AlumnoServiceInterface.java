/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.servicios;

import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;

/**
 *  Interfaz para el servicio de alumno.
 */
public interface AlumnoServiceInterface {
    
    /**
     * Función para listar alumnos en la base de datos.
     * @return  Lista de alumnos.
     */
    public List<Alumno> listarAlumnos();
    
    /**
     * Función para añadir un alumno.
     * @param alumno objeto alumno a añadir.
     */
    public void anadirAlumno(Alumno alumno); 
    
    /**
     * Función para eliminar un alumno.
     * @param alumno objeto alumno a eliminar.
     */
    public void eliminarAlumno(Alumno alumno); 
    
    /**
     * Función que busca un objeto alumno en la base de datos.
     * @param alumno objeto alumno a buscar.
     * @return el objeto alumno si exist o null si no existe.
     */
    public Alumno buscarAlumno(Alumno alumno); 
    
    /**
     * Función que busca un objeto alumno en la base de datos.
     * @param username username a buscar
     * @return el objeto alumno si exist o null si no existe.
     */
    public Alumno buscarAlumnoPorUsername(String username); 
}
