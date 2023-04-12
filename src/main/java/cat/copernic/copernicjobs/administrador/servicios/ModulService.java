/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.ModuloDAO;
import cat.copernic.copernicjobs.model.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de servicio que implementa la interfaz ModulServiceInterface para
 * gestionar las operaciones de módulos en el sistema. Esta clase es un servicio
 * de Spring que proporciona métodos para realizar operaciones CRUD (Crear,
 * Leer, Actualizar, Eliminar) en la entidad Modulo.
 *
 * @author joang
 */
@Service
public class ModulService implements ModulServiceInterface {

    public void setModulo(ModuloDAO modulo) {
        this.modulo = modulo;
    }

    @Autowired
    private ModuloDAO modulo;

    /**
     * Obtiene una lista de todos los módulos en el sistema.
     *
     * @return Lista de objetos Modulo que representan a todos los módulos en el
     * sistema.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Modulo> llistarModuls() {
        return (List<Modulo>) modulo.findAll();
    }

    /**
     * Añade un nuevo módulo al sistema.
     *
     * @param modulo Objeto Modulo que representa al módulo a añadir.
     */
    @Override
    @Transactional
    public void afegirModul(Modulo modulo) {
        this.modulo.save(modulo);
    }

    /**
     * Elimina un módulo del sistema.
     *
     * @param modulo Objeto Modulo que representa al módulo a eliminar.
     */
    @Override
    @Transactional
    public void eliminarModul(Modulo modulo) {
        this.modulo.delete(modulo);
    }

    /**
     * Busca un módulo por su ID en el sistema.
     *
     * @param modulo Objeto Modulo que contiene el ID del módulo a buscar.
     * @return Objeto Modulo que representa al módulo encontrado, o null si no
     * se encontró.
     */
    @Override
    @Transactional(readOnly = true)
    public Modulo cercarModul(Modulo modulo) {
        return this.modulo.findById(modulo.getID()).orElse(null);
    }

}
