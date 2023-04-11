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

    /**
     * Función para listar noticias en la base de datos.
     *
     * @return Lista de noticia.
     */
    public List<Noticia> llistarNoticies(); //Mètode que implementarem per llistar noticies

    /**
     * Función para añadir una noticia.
     *
     * @param noticia objeto noticia a añadir.
     */
    public void afegirNoticia(Noticia noticia); //Mètode que implementarem per afegir una noticia

    /**
     * Función para eliminar una noticia.
     *
     * @param noticia objeto noticia a eliminar.
     */
    public void eliminarNoticia(Noticia noticia); //Mètode que implementarem per eliminar una noticia

    /**
     * Función que busca un objeto noticia en la base de datos.
     *
     * @param noticia objeto noticia a buscar.
     * @return el objeto noticia si existe o null si no existe.
     */
    public Noticia cercarNoticia(Noticia noticia); //Mètode que implementarem per cercar una noticia
}
