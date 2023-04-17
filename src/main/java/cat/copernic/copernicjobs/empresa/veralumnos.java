/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.administrador.controladores.*;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class veralumnos {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private InscripcionService inscripcionService;

    /**
     *
     * Método que procesa la petición GET de visualizar los alumnos inscritos en
     * una oferta.
     *
     * @param model El objeto Model utilizado para almacenar y pasar datos a la
     * vista.
     * @param username El objeto UserDetails que representa al usuario que está
     * realizando la búsqueda.
     * @param oferta El objeto Oferta que se está visualizando.
     * @return La plantilla HTML de la página principal de la empresa con la
     * lista de alumnos inscritos en la oferta.
     */
    @PreAuthorize("hasAuthority('empresa')")
    @GetMapping("/veralumnos/{id}")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username, Oferta oferta) {

        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "verAlumnos";
        List<Inscripcion> i = inscripcionService.buscarInscripcionPorOfertaId(oferta.getId());
        List<Alumno> alumnos = new ArrayList();
        for (Inscripcion inscripcion : i) {
            alumnos.add(inscripcion.getAlumno());
        }
        model.addAttribute("alumnos", alumnos);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Inici", username);
    }

}
