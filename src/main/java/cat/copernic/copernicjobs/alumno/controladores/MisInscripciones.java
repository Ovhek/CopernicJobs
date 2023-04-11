/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import cat.copernic.copernicjobs.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Cole
 */
@Controller
public class MisInscripciones {

    @Autowired
    InscripcionService inscripcionService;

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    AdministradorService administradorService;

    /**
     *
     * Método que devuelve la vista de las inscripciones del alumno.
     *
     * @PreAuthorize Comprueba si el usuario tiene el rol de "alumne".
     * @GetMapping Anotación que mapea la petición GET a la URL
     * "/alumne/inscripcions" a este método.
     * @param model Objeto Model utilizado para pasar atributos a la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @param request Objeto HttpServletRequest utilizado para recuperar el
     * objeto inputFlashMap.
     * @return Cadena de texto que representa la plantilla HTML de la vista de
     * las inscripciones del alumno.
     */
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/inscripcions")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        Usuario alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());

        if (alumno == null) {
            alumno = administradorService.buscarAdministradorPorUsername(username.getUsername());
        }

        List<Inscripcion> inscripciones = inscripcionService.buscarInscripcionPorAlumnoId(alumno.getId());

        if (inputFlashMap != null) {
            List<Oferta> ofertas = (List<Oferta>) inputFlashMap.get("ofertas");
            inscripciones = inscripciones.stream()
                    .filter(inscripcion -> {
                        for (Oferta oferta : ofertas) {
                            if (inscripcion.getOferta().getId() == oferta.getId()) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "misInscripciones";
        model.addAttribute("inscripciones", inscripciones);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Les meves inscripcions", username);
    }
}
