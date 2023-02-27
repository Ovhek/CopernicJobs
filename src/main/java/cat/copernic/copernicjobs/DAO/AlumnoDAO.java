/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.DAO;

import cat.copernic.copernicjobs.model.Alumno;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO para la integración de la entidad Alumno con la base de datos.
 */
public interface AlumnoDAO extends CrudRepository<Alumno,Integer>{ 

}
