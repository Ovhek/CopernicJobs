/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.general.servicios.IncidenciaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Incidencia;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cole
 */
@Controller
public class CrearIncidencia {

    @Autowired
    IncidenciaService incidenciaService;

    @GetMapping("/alumne/crearIncidencia")
    public String alumneIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username, request);
    }

    @GetMapping("/empresa/crearIncidencia")
    public String empresaIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username,request);
    }

    public String inicio(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest test) {

        String url = test.getRequestURL().toString();
        String rol = username.getAuthorities().iterator().next().getAuthority();
        NavBarType navbarType = null;

        switch (rol.toLowerCase()) {
            case "alumne":
                navbarType = NavBarType.ALUMNO;
                break;
            case "empresa":
                navbarType = NavBarType.EMPRESA;
                break;
            default:
                navbarType = NavBarType.ADMINISTRADOR;
        }
        if(navbarType == NavBarType.ADMINISTRADOR && url.contains("alumne")) navbarType = NavBarType.ALUMNO;
        if(navbarType == NavBarType.ADMINISTRADOR && url.contains("empresa")) navbarType = NavBarType.EMPRESA;
        //Ruta donde está el archivo html 
        String ruta = "";
        //nombre del archivo html
        String archivo = "crearIncidencia";

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, navbarType, ruta, archivo, "Crear Incidencia", username);
    }

    @PostMapping("/crearIncidencia")
    public String crearIncidencia(Incidencia incidencia, Model model, HttpServletRequest request, RedirectAttributes redirect) {
        
        String crearIncidenciaRedirect = "";
        var originUrl = request.getHeader("referer");
        if(originUrl.contains("alumne")) crearIncidenciaRedirect = "/alumne";
        if(originUrl.contains("empresa")) crearIncidenciaRedirect = "/empresa";
        
        incidencia.setFechaIncidencia(LocalDate.now());

        incidenciaService.anadirIncidencia(incidencia);

        redirect.addFlashAttribute("creado","S'ha enviat l'incidencia");
        model.addAttribute("creado", true);
        return "redirect:"+crearIncidenciaRedirect+"/crearIncidencia";

    }
}
