/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Inscripcion;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Albert
 */ 
public interface InscripcionDAO extends CrudRepository<Inscripcion,Integer>{ 
    
    //Aqui podrem afegir altres mètodes que necessitem i que no estiguin definits a la interface CrudRepository,
    //com el següent mètode, findByNom que retornarà els gossos amb el nom passat per paràmetre
    List<Inscripcion> findByNom(String nom);
} 