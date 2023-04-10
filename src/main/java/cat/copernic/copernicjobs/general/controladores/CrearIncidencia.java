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
 * Controlador para la creación de Incidencias.
 * @author Alex
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

    /**
     *
     * Método que redirige a la página de inicio para crear una incidencia.
     *
     * @param incidencia objeto de tipo Incidencia que se va a crear
     * @param model objeto de tipo Model para almacenar los datos a mostrar en
     * la vista
     * @param username objeto de tipo UserDetails que contiene los detalles del
     * usuario autenticado
     * @param request objeto de tipo HttpServletRequest que contiene la
     * información de la solicitud HTTP
     * @return una cadena de texto que indica la dirección de la página a
     * mostrar
     */
    @GetMapping("/alumne/crearIncidencia")
    public String alumneIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username, request);
    }

    /**
     *
     * Método para manejar la solicitud GET de creación de una incidencia de la
     * empresa.
     *
     * @param incidencia objeto Incidencia que contiene la información de la
     * incidencia a crear
     * @param model objeto Model de Spring que permite pasar datos a la vista
     * @param username objeto UserDetails que contiene la información del
     * usuario autenticado
     * @param request objeto HttpServletRequest que representa la solicitud HTTP
     * realizada
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta
     */
    @GetMapping("/empresa/crearIncidencia")
    public String empresaIncidencia(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        return inicio(incidencia, model, username, request);
    }

    /**
     *
     * Método que carga la pantalla principal de la aplicación y añade la vista
     * para crear una incidencia.
     *
     * @param incidencia objeto Incidencia que contiene la información de la
     * incidencia a crear
     * @param model objeto Model de Spring que permite pasar datos a la vista
     * @param username objeto UserDetails que contiene la información del
     * usuario autenticado
     * @param request objeto HttpServletRequest que representa la solicitud HTTP
     * realizada
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta
     */
    public String inicio(Incidencia incidencia, Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null && inputFlashMap.containsKey("errores")) {
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

    /**
     *
     * Método para manejar la solicitud POST de creación de una incidencia.
     *
     * @param request objeto HttpServletRequest que representa la solicitud HTTP
     * realizada
     * @param username objeto UserDetails que contiene la información del
     * usuario autenticado
     * @param incidencia objeto Incidencia que contiene la información de la
     * incidencia a crear
     * @param errores objeto Errors de Spring que contiene información sobre
     * errores de validación
     * @param model objeto Model de Spring que permite pasar datos a la vista
     * @param redirect objeto RedirectAttributes de Spring que permite agregar
     * atributos para la redirección
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta
     */
    @PostMapping("/crearIncidencia")
    public String crearIncidencia(HttpServletRequest request, @AuthenticationPrincipal UserDetails username, @Valid Incidencia incidencia, Errors errores, Model model, RedirectAttributes redirect) {

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
        if (usuario == null) {
            empresaService.buscarPorUsername(username.getUsername());
        }
        if (usuario == null) {
            administradorService.buscarAdministradorPorUsername(username.getUsername());
        }

        incidencia.setUsuario(usuario);
        incidenciaService.anadirIncidencia(incidencia);

        redirect.addFlashAttribute("creado", "S'ha enviat l'incidencia");
        model.addAttribute("creado", true);
        return "redirect:" + crearIncidenciaRedirect + "/crearIncidencia";

    }
}
