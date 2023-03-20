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
    
    public List<Modulo> llistarModuls();
    
    public void afegirModul(Modulo modulo);
    
    public void eliminarModul(Modulo modulo);
    
    public Modulo cercarModul(Modulo modulo);
}
