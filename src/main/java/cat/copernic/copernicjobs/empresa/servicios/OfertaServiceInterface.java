/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa.servicios;

import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;
    
/**
 *
 * @author Albert
 */
public interface OfertaServiceInterface {
      
    public List<Oferta> llistarOfertas(); //Mètode que implementarem per llistar gossos
    
    public void afegirOferta(Oferta oferta); //Mètode que implementarem per afegir un gos
    
    public void eliminarOferta(Oferta oferta); //Mètode que implementarem per eliminar un gos
    
    public Oferta cercarOferta(Oferta oferta); //Mètode que implementarem per cercar un gos
    
    public List<Oferta> llistarOfertasUltimaSemana();
    
    public List<Oferta> ordenarOfertasAlfabetico();
    
    public List<Oferta> filtrarOfertasOrdenacion(String busqueda, String ordenacion);
    
    public List<Oferta> listarPorNombre(String nombre);
    
    
}
