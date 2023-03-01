/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.DAO;

import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO para la integración de la entidad Inicidencia con la base de datos.
 */
public interface IncidenciaDAO extends CrudRepository<Incidencia,Integer>{ 
    //Aqui podrem afegir altres mètodes que necessitem i que no estiguin definits a la interface CrudRepository,
    //com el següent mètode, findByNom que retornarà els gossos amb el nom passat per paràmetre
    List<Incidencia> findByDescripcion(String descripcion);
}
