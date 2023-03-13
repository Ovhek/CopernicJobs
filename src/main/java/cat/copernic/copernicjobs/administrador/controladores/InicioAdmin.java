/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Oferta;
import cat.copernic.copernicjobs.model.Persona;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author joang
 */
@Controller
public class InicioAdmin {

    /*Modifiquem l'atribut noticiaDAO que teniem anteriorment per un atribut de tipus NoticiaService, ja que
     *ara accedirem a la base de dades mitjançant els mètodes d'aquesta classe, afegint una nova capa al 
     *nostre projecte.
     */
    @Autowired//Anotació que injecta tots els mètodes i possibles dependències de NoticiaService al controlador
    private NoticiaService noticiaService;

    @Autowired
    private AlumnoDAO alumnoDAO;

    @Autowired
    private EmpresaDAO empresaDAO;

    @Autowired
    private OfertaDAO ofertaDAO;

    @GetMapping("/inicioAdmin") //Pàgina inicial d'admin
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "inicioAdmin";

        //llistarNoticies() retorna el llistat d'objectes noticia guardats en la taula noticies de la BBDD    
        model.addAttribute("noticias", noticiaService.llistarNoticies());
        model.addAttribute("validaciones", alumnoDAO.findAll());
        model.addAttribute("validaciones2", empresaDAO.findAll());
        model.addAttribute("validaciones3", ofertaDAO.findAll());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    /*Definim el mètode per mostrar la pàgina amb el forumlari de les dades de la noticia passada com a paràmetre.
     *Aquesta noticia, si no existeix, es crearà de manera automàtica amb els seu atributs buits (recordem que el 
     *constructor construeix un objecte buit) en el moment que executem aquest mètode.
     *
     */
    @GetMapping("/crearNoticia") //URL a la pàgina amb el formulari de les dades de la noticia
    public String crearNoticia(Noticia noticia, Model model) {

        String ruta = "administrador/";

        String archivo = "crearNoticia";

        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    /*Definim el mètode per assignar els valors introduïts en el formulari a l'objecte noticia
     *passat com a paràmetre.
     *
     *L'anotació @PostMapping, indica al sistema que el mètode que fem servir per enviar les dades és
     *post. Com a paràmetre hem de passar el valor de l'action del formulari, d'aquesta manera el sistema 
     *identifica el mètode al qual ha d'enviar les dades introduïdes mitjançant el formulari.
     */
    @PostMapping("/guardarNoticia") //action=guardarNoticia
    public String guardarNoticia(Noticia noticia) {
        noticia.setFechaHora(LocalDate.now());

        noticiaService.afegirNoticia(noticia); //Afegim la noticia passada per paràmetre a la base de dades

        return "redirect:/inicioAdmin"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    /*Definim el mètode que ens retornarà la pàgina crearNoticia on se'ns mostraran les dades de la noticia
     *amb l'idnoticia que enviem des de la pàgina inici.
     *El sistema Spring associa l'idnoticia passada com a paràmetre en @GetMapping a la noticia 
     *passat com a paràmetre en el mètode editar i crida automàticament al mètode setIdnoticia 
     *de la classe Gos per fer aquesta associació, és a dir, el que fa és noticia.setIdnoticia(idnoticia).
     *IMPORTANT: id ha de tenir el mateix nom que l'atribut id de la classe a la que fa referència,
     *en el nostre cas la classe Noticia.
     */
    @GetMapping("/editar/{id}")
    public String editar(Noticia noticia, Model model) {

        /*Cerquem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode cercarNoticia de la capa de servei.*/
        String ruta = "administrador/";

        String archivo = "crearNoticia";

        model.addAttribute("noticia", noticiaService.cercarNoticia(noticia));

        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    /*Definim el mètode per eliminar la noticia en la base de dades i finalment retornar
     *a la pàgina d'inici. La noticia l'eliminarem mitjançant el mètode eliminarNoticia de
     *la classe NoticiaService.
     *El sistema per associar l'id de la noticia a l'objecte noticia passada per paràmetre, és el mateix
     *que el del mètode editar.
     *IMPORTANT: id ha de tenir el mateix nom que l'atribut id de la classe a la que fa referència,
     *en el nostre cas la classe Noticia.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(Noticia noticia) {

        /*Eliminem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode eliminarNoticia de la capa de servei.*/
        noticiaService.eliminarNoticia(noticia);

        return "redirect:/inicioAdmin"; //Retornem a la pàgina inicial mitjançant redirect
    }
}
