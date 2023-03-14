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

    @Autowired
    AlumnoDAO alumnoDAO;

    @Transactional(readOnly=true) 
    @Override
    public List<Alumno> listarAlumnos() {
        return (List<Alumno>) alumnoDAO.findAllByBaja(false);
    }

    @Transactional 
    @Override
    public void anadirAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    @Transactional
    @Override
    public void eliminarAlumno(Alumno alumno) {
        alumnoDAO.save(alumno);
    }

    @Transactional(readOnly=true) 
    @Override
    public Alumno buscarAlumno(Alumno alumno) {
        return alumnoDAO.findById(alumno.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Alumno buscarAlumnoPorUsername(String username) {
        return alumnoDAO.findByUsername(username);
    }
}
