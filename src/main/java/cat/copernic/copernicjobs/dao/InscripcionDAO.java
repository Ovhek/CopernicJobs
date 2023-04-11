/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Inscripcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO para la integración de la entidad Inicidencia con la base de datos.
 */
public interface InscripcionDAO extends JpaRepository<Inscripcion, Integer> {

    /**
     *
     * Devuelve una lista de todas las inscripciones realizadas por un alumno
     * con el id especificado.
     *
     * @param id el id del alumno del que se desea obtener las inscripciones
     * @return una lista de inscripciones realizadas por el alumno con el id
     * especificado
     */
    List<Inscripcion> findAllByAlumnoId(int id);

    /**
     *
     * Encuentra todas las inscripciones correspondientes a una oferta
     * determinada
     *
     * @param id el identificador de la oferta a buscar
     * @return una lista de Inscripcion que contiene todas las inscripciones
     * correspondientes a la oferta dada
     */
    List<Inscripcion> findAllByOfertaId(int id);
}
