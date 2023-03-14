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

    @GetMapping("/veureOfertaAlumne/{id}")
    public String inicio(Oferta ofertaGet, Model model) {

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        int alumnoId = 1;

        int ofertaId = 1;
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
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Oferta - "+oferta.getTituloOferta());
    }

    @PostMapping("/in")
    public String inscribirAlumnoAOFerta(Oferta ofertaGet) {

        alumnoTemp.setId(1);
        Alumno alumno = alumnoService.buscarAlumno(alumnoTemp);

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAlumno(alumno);
        inscripcion.setOferta(oferta);
        inscripcion.setEstado(0);
        inscripcion.setFechaInscripcion(LocalDate.now());

        inscripcionService.anadirInscripcion(inscripcion);

        return "redirect:/veureOfertaAlumne/"+oferta.getId();
    }

    @PostMapping("/des")
    public String desinscribirAlumnoAOFerta(Oferta ofertaGet) {

        alumnoTemp.setId(1);
        Alumno alumno = alumnoService.buscarAlumno(alumnoTemp);

        Oferta oferta = ofertaService.cercarOferta(ofertaGet);

        List<Inscripcion> inscripciones = inscripcionService.buscarInscripcionPorOfertaId(oferta.getId());
        Inscripcion inscripcionABorrar = inscripciones.stream().filter(inscripcion_ -> inscripcion_.getAlumno().equals(alumno)).findFirst().get();
        if(inscripcionABorrar != null)inscripcionService.eliminarInscripcion(inscripcionABorrar);

        return "redirect:/veureOfertaAlumne/"+oferta.getId();
    }
}
