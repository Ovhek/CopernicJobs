/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cole
 */
@Controller
public class VerOfertaAlumno {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private AlumnoService alumnoService;

    private Alumno alumnoTemp = new Alumno();

    /**
     *
     * Método que devuelve la vista de una oferta específica para un alumno.
     *
     * @PreAuthorize Comprueba si el usuario tiene el rol de "alumne".
     * @GetMapping Anotación que mapea la petición GET a la URL
     * "/alumne/veureOfertaAlumne/{id}" a este método.
     * @param ofertaGet Objeto Oferta utilizado para obtener la oferta concreta.
     * @param model Objeto Model utilizado para pasar atributos a la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return Cadena de texto que representa la plantilla HTML de la vista de
     * una oferta específica para un alumno.
     */
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/veureOfertaAlumne/{id}")
    public String inicio(Oferta ofertaGet, Model model, @AuthenticationPrincipal UserDetails username) {

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        int alumnoId = alumnoService.buscarAlumnoPorUsername(username.getUsername()).getId();

        int ofertaId = ofertaGet.getId();
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "verOferta";

        var inscripciones = inscripcionService.buscarInscripcionPorOfertaId(oferta.getId());
        var alumnosInscritos = inscripciones.size();

        var alumnoInscrito = inscripciones.stream().map(Inscripcion::getAlumno).anyMatch(alumno -> alumno.getId() == alumnoId);

        model.addAttribute("oferta", oferta);

        model.addAllAttributes(new HashMap<String, Object>() {
            {
                put("alumnoInscrito", alumnoInscrito);
                put("alumnesInscrits", inscripciones.size());
            }
        });

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Oferta - " + oferta.getTituloOferta(), username);
    }

    /**
     *
     * Método que inscribe a un alumno en una oferta.
     *
     * @PostMapping Anotación que mapea la petición POST a la URL "/in" a este
     * método.
     * @param ofertaGet Objeto Oferta utilizado para obtener la oferta en la que
     * se va a inscribir el alumno.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return Cadena de texto que representa la redirección a la vista de una
     * oferta específica para un alumno.
     */
    @PostMapping("/in")
    public String inscribirAlumnoAOFerta(Oferta ofertaGet, @AuthenticationPrincipal UserDetails username) {

        Alumno alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAlumno(alumno);
        inscripcion.setOferta(oferta);
        inscripcion.setEstado(0);
        inscripcion.setFechaInscripcion(LocalDate.now());

        inscripcionService.anadirInscripcion(inscripcion);

        return "redirect:/alumne/veureOfertaAlumne/" + oferta.getId();
    }

    /**
     *
     * Método que se encarga de eliminar la inscripción de un alumno a una
     * oferta determinada.
     *
     * @param ofertaGet la oferta de la cual se desea desinscribir el alumno.
     * @param username el nombre del usuario autenticado que desea realizar la
     * desinscripción.
     * @return la redirección a la vista de la oferta de donde se ha realizado
     * la desinscripción.
     */
    @PostMapping("/des")
    public String desinscribirAlumnoAOFerta(Oferta ofertaGet, @AuthenticationPrincipal UserDetails username) {

        Alumno alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        List<Inscripcion> inscripciones = inscripcionService.buscarInscripcionPorOfertaId(oferta.getId());
        Inscripcion inscripcionABorrar = inscripciones.stream().filter(inscripcion_ -> inscripcion_.getAlumno().equals(alumno)).findFirst().get();
        if (inscripcionABorrar != null) {
            inscripcionService.eliminarInscripcion(inscripcionABorrar);
        }

        return "redirect:/alumne/veureOfertaAlumne/" + oferta.getId();
    }
}
