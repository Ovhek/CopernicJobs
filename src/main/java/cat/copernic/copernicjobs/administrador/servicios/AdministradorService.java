/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.model.Administrador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de servicio que implementa la interfaz AdministradorServiceInterface
 * para gestionar las operaciones de administradores en el sistema. Esta clase
 * es un servicio de Spring que proporciona métodos para realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) en la entidad Administrador.
 *
 * @author joang
 */
@Service
public class AdministradorService implements AdministradorServiceInterface {

    @Autowired
    AdministradorDAO administradorDAO; // Objeto de acceso a datos para la entidad Administrador

    /**
     * Establece el objeto AdministradorDAO que se utilizará para acceder a los
     * datos de la entidad Administrador.
     *
     * @param administradorDAO Objeto AdministradorDAO a establecer.
     */
    public void setAdministradorDAO(AdministradorDAO administradorDAO) {
        this.administradorDAO = administradorDAO;
    }

    /**
     * Obtiene una lista de todos los administradores en el sistema.
     *
     * @return Lista de objetos Administrador que representan a todos los
     * administradores en el sistema.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Administrador> listarAdministradors() {
        return (List<Administrador>) administradorDAO.findAll();
    }

    /**
     * Añade un nuevo administrador al sistema.
     *
     * @param administrador Objeto Administrador que representa al administrador
     * a añadir.
     */
    @Override
    public void anadirAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
    }

    /**
     * Elimina un administrador del sistema.
     *
     * @param administrador Objeto Administrador que representa al administrador
     * a eliminar.
     */
    @Override
    public void eliminarAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
    }

    /**
     * Busca un administrador por su ID en el sistema.
     *
     * @param administrador Objeto Administrador que contiene el ID del
     * administrador a buscar.
     * @return Objeto Administrador que representa al administrador encontrado,
     * o null si no se encontró.
     */
    @Transactional(readOnly = true)
    @Override
    public Administrador buscarAdministrador(Administrador administrador) {
        return administradorDAO.findById(administrador.getId()).orElse(null);
    }

    /**
     * Busca un administrador por su nombre de usuario en el sistema.
     *
     * @param username Nombre de usuario del administrador a buscar.
     * @return Objeto Administrador que representa al administrador encontrado,
     * o null si no se encontró.
     */
    @Transactional(readOnly = true)
    @Override
    public Administrador buscarAdministradorPorUsername(String username) {
        return administradorDAO.findByUsername(username);
    }

}
