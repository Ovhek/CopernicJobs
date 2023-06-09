/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.servicios.IncidenciaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase controlador que maneja la visualización y gestión de incidencias.
 * Requiere autorización de tipo 'administrador' para acceder a los métodos.
 * Utiliza archivos HTML ubicados en la ruta 'administrador/' como plantillas de
 * vista. Las incidencias son gestionadas a través de la capa de servicio
 * IncidenciaService.
 *
 * @author joang
 */
@Controller
public class VerIncidencias {

    @Autowired
    private IncidenciaService incidenciaService;

    /**
     * Método que maneja la solicitud GET para la ruta '/verIncidencias' y
     * muestra la lista de incidencias.
     *
     * @param model Objeto Model que permite pasar datos a la vista.
     * @param username Objeto UserDetails que contiene la información del
     * usuario autenticado.
     * @return Nombre del archivo HTML de la vista principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verIncidencias")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verIncidencias";

        model.addAttribute("incidencias", incidenciaService.listarIncidencias());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    /**
     * Método que maneja la solicitud GET para la ruta '/verIncidencia/{id}' y
     * muestra los detalles de una incidencia específica.
     *
     * @param incidencia Objeto Incidencia que contiene la incidencia a
     * visualizar.
     * @param model Objeto Model que permite pasar datos a la vista.
     * @param username Objeto UserDetails que contiene la información del
     * usuario autenticado.
     * @return Nombre del archivo HTML de la vista de detalles de la incidencia.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verIncidencia/{id}")
    public String ver(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verIncidencia";

        model.addAttribute("incidencia", incidenciaService.buscarIncidencia(incidencia));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);

    }

    /**
     * Método que maneja la solicitud GET para la ruta
     * '/eliminarIncidencia/{id}' y elimina una incidencia específica.
     *
     * @param incidencia Objeto Incidencia que contiene la incidencia a
     * eliminar.
     * @param username Objeto UserDetails que contiene la información del
     * usuario autenticado.
     * @return Redirección a la página de lista de incidencias después de la
     * eliminación.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/eliminarIncidencia/{id}")
    public String eliminar(Incidencia incidencia, @AuthenticationPrincipal UserDetails username) {

        /*Eliminem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode eliminarNoticia de la capa de servei.*/
        incidenciaService.eliminarIncidencia(incidencia);

        return "redirect:/verIncidencias"; //Retornem a la pàgina inicial de les incidencies mitjançant redirect
    }
}
