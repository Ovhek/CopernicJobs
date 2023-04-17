/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.dao.UsuarioDAO;
import cat.copernic.copernicjobs.model.Empresa;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Alex
 * DAO para la integración de la entidad Usuario con la base de datos.
 */
public interface EmpresaDAO extends UsuarioDAO<Empresa, Serializable> {

    /**
     *
     * Devuelve la Empresa que coincide con el nombre proporcionado.
     *
     * @param nombre el nombre de la Empresa a buscar.
     * @return la Empresa que coincide con el nombre proporcionado, o null si no
     * se encuentra ninguna coincidencia.
     */
    Empresa findByNombreEmpresa(String nombre);

    List<Empresa> findAllByBaja(boolean baja);
}
