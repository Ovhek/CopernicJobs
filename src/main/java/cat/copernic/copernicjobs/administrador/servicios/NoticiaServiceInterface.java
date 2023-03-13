/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.model.Noticia;
import java.util.List;

/**
 *
 * @author joang
 */
//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface NoticiaServiceInterface {
    
    public List<Noticia> llistarNoticies(); //Mètode que implementarem per llistar noticies
    
    public void afegirNoticia(Noticia noticia); //Mètode que implementarem per afegir una noticia
    
    public void eliminarNoticia(Noticia noticia); //Mètode que implementarem per eliminar una noticia
    
    public Noticia cercarNoticia(Noticia noticia); //Mètode que implementarem per cercar una noticia
}
