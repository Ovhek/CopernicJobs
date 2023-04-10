/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.dao.RolModuloDAO;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.RolModulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para usar el DAO de la tabla RolModulo y aplicar logica.
 *
 * @author Alex
 */
@Service
public class RolModuloService implements RolModuloServiceInterface {

    @Autowired
    RolModuloDAO rolModuloDao;

    /**
     *
     * Asigna un objeto RolModuloDAO al atributo de la clase rolModuloDao.
     *
     * @param rolModuloDao el objeto RolModuloDAO que se desea asignar.
     */
    public void setRolModuloDao(RolModuloDAO rolModuloDao) {
        this.rolModuloDao = rolModuloDao;
    }

    /**
     *
     * Obtiene la lista de módulos asociados a un rol determinado.
     *
     * @param nom el nombre del rol del que se desean obtener los módulos.
     * @return una lista de objetos Modulo que se corresponden con los módulos
     * asociados al rol especificado.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Modulo> findModulosByRolNom(String nom) {
        return rolModuloDao.findModulosByNombreRol(nom);
    }

    /**
     *
     * Obtiene la lista de roles asociados a un módulo determinado.
     *
     * @param nom el nombre del módulo del que se desean obtener los roles.
     * @return una lista de objetos Rol que se corresponden con los roles
     * asociados al módulo especificado.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Rol> findRoleslByModuloNom(String nom) {
        return rolModuloDao.findRolesByNombreModulo(nom);
    }

    /**
     *
     * Obtiene una lista de todos los objetos RolModulo almacenados en la base
     * de datos.
     *
     * @return una lista de todos los objetos RolModulo almacenados en la base
     * de datos.
     */
    @Transactional(readOnly = true)
    public List<RolModulo> findAll() {
        return rolModuloDao.findAll();
    }

    /**
     *
     * Guarda un objeto RolModulo en la base de datos.
     *
     * @param rolModulo el objeto RolModulo que se desea guardar.
     */
    @Transactional
    public void guardar(RolModulo rolModulo) {
        rolModuloDao.save(rolModulo);
    }

    /**
     *
     * Elimina un objeto RolModulo de la base de datos.
     *
     * @param rolModulo el objeto RolModulo que se desea eliminar.
     */
    @Transactional
    public void eliminar(RolModulo rolModulo) {
        rolModuloDao.delete(rolModulo);
    }

    /**
     *
     * Guarda una lista de objetos RolModulo en la base de datos.
     *
     * @param rolesModulos la lista de objetos RolModulo que se desea guardar.
     */
    public void guardarTodos(List<RolModulo> rolesModulos) {
        rolesModulos.forEach(rolModulo -> guardar(rolModulo));
    }

    /**
     *
     * Elimina una lista de objetos RolModulo de la base de datos.
     *
     * @param rolModulosAEliminar la lista de objetos RolModulo que se desea
     * eliminar.
     */
    public void eliminarTodos(List<RolModulo> rolModulosAEliminar) {
        rolModulosAEliminar.forEach(rolModulo -> eliminar(rolModulo));
    }
}
