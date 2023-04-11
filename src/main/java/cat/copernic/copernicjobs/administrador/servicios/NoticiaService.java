/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.NoticiaDAO;
import cat.copernic.copernicjobs.model.Noticia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta clase implementa la interfaz NoticiaServiceInterface y proporciona los
 * métodos para trabajar con la entidad Noticia en la capa de servicio. Utiliza
 * un objeto NoticiaDAO para acceder a la capa de datos y gestionar las
 * transacciones SQL.
 *
 * @author joang
 */
@Service
public class NoticiaService implements NoticiaServiceInterface {

    /**
     * Atributo que define un objeto NoticiaDAO. A través de este atributo, la capa de servicio accede a la capa de datos.
     */
    @Autowired
    private NoticiaDAO noticia;

    /**
     * Este método lista todas las noticias de la tabla noticia en la base de datos copernicjobs.
     *
     * @return Lista de objetos Noticia que representa las noticias en la base de datos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Noticia> llistarNoticies() {
        return (List<Noticia>) noticia.findAll();
    }

    /**
     * Este método agrega una noticia a la tabla noticia en la base de datos copernicjobs.
     *
     * @param noticia Objeto Noticia que representa la noticia a agregar en la base de datos.
     */
    @Override
    @Transactional
    public void afegirNoticia(Noticia noticia) {
        this.noticia.save(noticia);
    }

    /**
     * Este método elimina una noticia de la tabla noticia en la base de datos copernicjobs.
     *
     * @param noticia Objeto Noticia que representa la noticia a eliminar de la base de datos.
     */
    @Override
    @Transactional
    public void eliminarNoticia(Noticia noticia) {
        this.noticia.delete(noticia);

    }

    /**
     * Este método busca una noticia en la tabla noticia en la base de datos copernicjobs.
     *
     * @param noticia Objeto Noticia que representa la noticia a buscar en la base de datos.
     * @return Objeto Noticia que representa la noticia encontrada en la base de datos, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Noticia cercarNoticia(Noticia noticia) {
        return this.noticia.findById(noticia.getId()).orElse(null);

    }
}
