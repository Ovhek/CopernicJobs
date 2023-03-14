/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;

/**
 *
 * @author joang
 */
public interface OfertaServiceInterface {
    
    public List<Oferta> llistarOfertes(); //Mètode que implementarem per llistar ofertes
    
    public void afegirOferta(Oferta oferta); //Mètode que implementarem per afegir una oferta
    
    public void eliminarOferta(Oferta oferta); //Mètode que implementarem per eliminar una oferta
    
    public Oferta cercarOferta(Oferta oferta); //Mètode que implementarem per cercar una oferta
}
