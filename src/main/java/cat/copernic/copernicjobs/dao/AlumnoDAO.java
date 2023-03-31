/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.dao.UsuarioDAO;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Cole
 */
/**
 * DAO para la integración de la entidad Usuario con la base de datos.
 */
public interface AlumnoDAO extends UsuarioDAO<Alumno, Integer> {

    /**
     *
     * Devuelve una lista de todos los objetos Alumno cuya propiedad "baja"
     * coincide con el valor booleano indicado.
     *
     * @param baja El valor booleano que indica si los objetos Alumno deben
     * tener su propiedad "baja" a true o a false.
     * @return Una lista de objetos Alumno cuya propiedad "baja" coincide con el
     * valor booleano indicado.
     */
    List<Alumno> findAllByBaja(boolean baja);

}
