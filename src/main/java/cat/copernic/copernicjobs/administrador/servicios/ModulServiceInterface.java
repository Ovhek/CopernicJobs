/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.model.Modulo;
import java.util.List;

/**
 *
 * @author joang
 */
public interface ModulServiceInterface {
    
    /**
     * Función para listar modulos en la base de datos.
     * @return  Lista de modulos.
     */
    public List<Modulo> llistarModuls();
    
    /**
     * Función para añadir un modulo.
     * @param modulo objeto modulo a añadir.
     */
    public void afegirModul(Modulo modulo);
    
    /**
     * Función para eliminar un modulo.
     * @param modulo objeto modulo a eliminar.
     */
    public void eliminarModul(Modulo modulo);
    
    /**
     * Función que busca un objeto modulo en la base de datos.
     * @param modulo objeto modulo a buscar.
     * @return el objeto modulo si existe o null si no existe.
     */
    public Modulo cercarModul(Modulo modulo);
}
