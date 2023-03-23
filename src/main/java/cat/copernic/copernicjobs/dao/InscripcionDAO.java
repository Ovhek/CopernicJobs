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
public interface InscripcionDAO extends JpaRepository<Inscripcion,Integer>{ 

    List<Inscripcion> findAllByAlumnoId(int id);
    List<Inscripcion> findAllByOfertaId(int id);
}
