/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.DAO;

import cat.copernic.copernicjobs.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cole
 */
/**
 * DAO para la integración de la entidad Usuario con la base de datos.
 */
public interface AlumnoDAO extends JpaRepository<Alumno,Integer>{ 
    
}
