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
 * Servicio para las incidencias
 *
 * @author Alex
 */
@Service
public class IncidenciaService implements IncidenciaServiceInterface {

    @Autowired
    IncidenciaDAO incidenciaDAO;

    /**
     *
     * Devuelve una lista de todas las incidencias.
     *
     * @return la lista de todas las incidencias.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Incidencia> listarIncidencias() {
        return (List<Incidencia>) incidenciaDAO.findAll();
    }

    /**
     *
     * Añade una incidencia.
     *
     * @param incidencia la incidencia a añadir.
     */
    @Transactional
    @Override
    public void anadirIncidencia(Incidencia incidencia) {
        incidenciaDAO.save(incidencia);
    }

    /**
     *
     * Elimina una incidencia.
     *
     * @param incidencia la incidencia a eliminar.
     */
    @Transactional
    @Override
    public void eliminarIncidencia(Incidencia incidencia) {
        incidenciaDAO.delete(incidencia);
    }

    /**
     *
     * Busca una incidencia.
     *
     * @param incidencia la incidencia a buscar.
     * @return la incidencia encontrada o null si no se encuentra.
     */
    @Transactional(readOnly = true)
    @Override
    public Incidencia buscarIncidencia(Incidencia incidencia) {
        return incidenciaDAO.findById(incidencia.getId()).orElse(null);
    }

}
