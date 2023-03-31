/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.servicios.IncidenciaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Cole
 */
@Controller
public class CrearIncidencia {

    @Autowired
    IncidenciaService incidenciaService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    EmpresaService empresaService;
    
    @Autowired
    AdministradorService administradorService;

    @GetMapping("/alumne/crearIncidencia")
    public String alumneIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username, request);
    }

    @GetMapping("/empresa/crearIncidencia")
    public String empresaIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username, request);
    }

    public String inicio(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(inputFlashMap != null && inputFlashMap.containsKey("errores")){
            model.addAttribute(inputFlashMap.get("errores"));
        }
        
        String url = request.getRequestURL().toString();
        String rol = username.getAuthorities().iterator().next().getAuthority();
        NavBarType navbarType = null;

        if (url.contains("alumne")) {
            navbarType = NavBarType.ALUMNO;
        }
        if (url.contains("empresa")) {
            navbarType = NavBarType.EMPRESA;
        }
        if (url.contains("administrador")) {
            navbarType = NavBarType.ADMINISTRADOR;
        }

        //Ruta donde está el archivo html 
        String ruta = "";
        //nombre del archivo html
        String archivo = "crearIncidencia";

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, navbarType, ruta, archivo, "Crear Incidencia", username);
    }

    @PostMapping("/crearIncidencia")
    public String crearIncidencia(HttpServletRequest request, @AuthenticationPrincipal UserDetails username, @Valid Incidencia incidencia,Errors errores, Model model, RedirectAttributes redirect) {

        String crearIncidenciaRedirect = "";
        var originUrl = request.getHeader("referer");
        if (originUrl.contains("alumne")) {
            crearIncidenciaRedirect = "/alumne";
        }
        if (originUrl.contains("empresa")) {
            crearIncidenciaRedirect = "/empresa";
        }

        if (errores.hasErrors()) {
            List<String> erroresString = new ArrayList<>();
            errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

            redirect.addFlashAttribute("errores", erroresString);
            return "redirect:" + crearIncidenciaRedirect + "/crearIncidencia";
        }
        
        incidencia.setFechaIncidencia(LocalDate.now());
        
        Usuario usuario = alumnoService.buscarAlumnoPorUsername(username.getUsername());
        if(usuario == null) empresaService.buscarPorUsername(username.getUsername());
        if(usuario == null) administradorService.buscarAdministradorPorUsername(username.getUsername());
        
        incidencia.setUsuario(usuario);
        incidenciaService.anadirIncidencia(incidencia);
        
        redirect.addFlashAttribute("creado", "S'ha enviat l'incidencia");
        model.addAttribute("creado", true);
        return "redirect:" + crearIncidenciaRedirect + "/crearIncidencia";

    }
}
