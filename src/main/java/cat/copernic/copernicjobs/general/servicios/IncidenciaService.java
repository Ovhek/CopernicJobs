/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

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
public class IncidenciaService implements IncidenciaServiceInterface{

    @Autowired
    IncidenciaDAO incidenciaDAO;
    
    @Transactional(readOnly=true) 
    @Override
    public List<Incidencia> listarIncidencias() {
        return (List<Incidencia>) incidenciaDAO.findAll();
    }

    @Transactional
    @Override
    public void anadirIncidencia(Incidencia incidencia) {
        incidenciaDAO.save(incidencia);
    }

    @Transactional
    @Override
    public void eliminarIncidencia(Incidencia incidencia) {
        incidenciaDAO.delete(incidencia);
    }

    @Transactional(readOnly=true) 
    @Override
    public Incidencia buscarIncidencia(Incidencia incidencia) {
        return incidenciaDAO.findById(incidencia.getId()).orElse(null);
    }
    
}
