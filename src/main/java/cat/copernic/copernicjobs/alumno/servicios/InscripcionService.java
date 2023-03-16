/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.servicios;

import cat.copernic.copernicjobs.dao.InscripcionDAO;
import cat.copernic.copernicjobs.model.Inscripcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cole
 */
@Service
public class InscripcionService implements InscripcionServiceInterface {

    @Autowired
    InscripcionDAO inscripcionDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> listarInscripciones() {
        return inscripcionDAO.findAll();
    }

    @Transactional
    @Override
    public void anadirInscripcion(Inscripcion inscripcion) {
        inscripcionDAO.save(inscripcion);
    }

    @Transactional
    @Override
    public void eliminarInscripcion(Inscripcion inscripcion) {
        inscripcionDAO.delete(inscripcion);
    }

    @Transactional(readOnly=true) 
    @Override
    public Inscripcion buscarInscripcion(Inscripcion inscripcion) {
        return inscripcionDAO.findById(inscripcion.getId()).orElse(null);
    }

    @Transactional(readOnly=true) 
    @Override
    public List<Inscripcion> buscarInscripcionPorAlumnoId(int id) {
        return inscripcionDAO.findAllByAlumnoId(id);
    }

    @Transactional(readOnly=true) 
    @Override
    public List<Inscripcion> buscarInscripcionPorOfertaId(int id) {
        return inscripcionDAO.findAllByOfertaId(id);
    }

}
