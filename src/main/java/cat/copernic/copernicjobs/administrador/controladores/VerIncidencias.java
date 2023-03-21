/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.servicios.IncidenciaService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Incidencia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerIncidencias {

    @Autowired
    private IncidenciaService incidenciaService;

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verIncidencias")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verIncidencias";

        model.addAttribute("incidencias", incidenciaService.listarIncidencias());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verIncidencia/{id}")
    public String ver(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verIncidencia";

        model.addAttribute("incidencia", incidenciaService.buscarIncidencia(incidencia));

        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);

    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/eliminarIncidencia/{id}")
    public String eliminar(Incidencia incidencia, @AuthenticationPrincipal UserDetails username) {

        /*Eliminem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode eliminarNoticia de la capa de servei.*/
        incidenciaService.eliminarIncidencia(incidencia);

        return "redirect:/verIncidencias"; //Retornem a la pàgina inicial de les incidencies mitjançant redirect
    }
}
