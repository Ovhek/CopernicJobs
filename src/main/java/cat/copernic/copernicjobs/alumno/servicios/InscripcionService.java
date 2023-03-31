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

    /**
     *
     * Devuelve una lista de todas las inscripciones registradas en el sistema.
     *
     * @return una lista de objetos Inscripcion que representan todas las
     * inscripciones.
     * @throws RuntimeException si ocurre un error al acceder a la base de
     * datos.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> listarInscripciones() {
        return inscripcionDAO.findAll();
    }

    /**
     *
     * Añade una nueva inscripción al sistema.
     *
     * @param inscripcion La inscripción a añadir.
     */
    @Transactional
    @Override
    public void anadirInscripcion(Inscripcion inscripcion) {
        inscripcionDAO.save(inscripcion);
    }

    /**
     *
     * Elimina la inscripción de un alumno en una oferta de trabajo en la base
     * de datos.
     *
     * @param inscripcion La inscripción a eliminar.
     */
    @Transactional
    @Override
    public void eliminarInscripcion(Inscripcion inscripcion) {
        inscripcionDAO.delete(inscripcion);
    }

    /**
     *
     * Busca una inscripción a partir de su id.
     *
     * @param inscripcion la inscripción a buscar.
     * @return la inscripción encontrada, o null si no existe.
     */
    @Transactional(readOnly = true)
    @Override
    public Inscripcion buscarInscripcion(Inscripcion inscripcion) {
        return inscripcionDAO.findById(inscripcion.getId()).orElse(null);
    }

    /**
     *
     * Devuelve una lista de todas las inscripciones asociadas a un alumno,
     * dados su id.
     *
     * @param id El id del alumno del que se desean obtener las inscripciones.
     * @return Una lista de objetos Inscripcion que corresponden a las
     * inscripciones del alumno especificado.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> buscarInscripcionPorAlumnoId(int id) {
        return inscripcionDAO.findAllByAlumnoId(id);
    }

    /**
     *
     * Busca todas las inscripciones asociadas a una oferta específica.
     *
     * @param id el id de la oferta a buscar
     * @return una lista de inscripciones asociadas a la oferta especificada
     */
    @Transactional(readOnly = true)
    @Override
    public List<Inscripcion> buscarInscripcionPorOfertaId(int id) {
        return inscripcionDAO.findAllByOfertaId(id);
    }

}
