/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.servicios;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.general.servicios.*;
import cat.copernic.copernicjobs.dao.IncidenciaDAO;
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

    @Autowired
    AlumnoDAO alumnoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listarAlumnos() {
        return (List<Alumno>) alumnoDAO.findAllByBaja(false);
    }

    @Override
    @Transactional
    public void anadirAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    @Override
    @Transactional
    public void eliminarAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscarAlumno(Alumno alumno) {
        return alumnoDAO.findById(alumno.getId()).orElse(null);
    }

}
