/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.servicios;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.general.servicios.*;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Incidencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cole
 */
@Service
public class AlumnoService implements AlumnoServiceInterface {

    public void setAlumnoDAO(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Autowired
    AlumnoDAO alumnoDAO;

    /**
     *
     * Método que devuelve una lista de alumnos que no han sido dados de baja
     *
     * @return lista de alumnos
     */
    @Transactional(readOnly = true)
    @Override
    public List<Alumno> listarAlumnos() {
        return (List<Alumno>) alumnoDAO.findAllByBaja(false);
    }

    /**
     *
     * Añade un objeto Alumno a la base de datos.
     *
     * @param alumno el objeto Alumno que se desea añadir a la base de datos
     */
    @Transactional
    @Override
    public void anadirAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    /**
     *
     * Elimina un objeto Alumno en la base de datos.
     *
     * @param alumno Objeto Alumno a eliminar.
     */
    @Transactional
    @Override
    public void eliminarAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    /**
     *
     * Busca un objeto Alumno en la base de datos por su ID.
     *
     * @param alumno El objeto Alumno a buscar.
     * @return El objeto Alumno encontrado o null si no existe en la base de
     * datos.
     */
    @Transactional(readOnly = true)
    @Override
    public Alumno buscarAlumno(Alumno alumno) {
        return alumnoDAO.findById(alumno.getId()).orElse(null);
    }

    /**
     *
     * Busca un objeto de tipo Alumno por su nombre de usuario.
     *
     * @param username el nombre de usuario del Alumno a buscar.
     * @return el Alumno correspondiente al nombre de usuario dado, o null si no
     * se encuentra.
     */
    @Transactional(readOnly = true)
    @Override
    public Alumno buscarAlumnoPorUsername(String username) {
        return alumnoDAO.findByUsername(username);
    }
}
