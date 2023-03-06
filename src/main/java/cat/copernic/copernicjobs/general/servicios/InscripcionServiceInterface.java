/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;

/**
 *
 * @author Albert
 */
public interface InscripcionServiceInterface {
      
    public List<Inscripcion> listarInscripciones(); //Mètode que implementarem per llistar gossos
    
    public void añadirInscripcion(Inscripcion inscripcion); //Mètode que implementarem per afegir un gos
    
    public void eliminarInscripcion(Inscripcion inscripcion); //Mètode que implementarem per eliminar un gos
    
    public Inscripcion buscarInscripcion(Inscripcion inscripcion); //Mètode que implementarem per cercar un gos
    


}
