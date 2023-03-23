/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.dao.InscripcionDAO;
import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import cat.copernic.copernicjobs.model.Usuario;
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
    
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/inscripcions")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        Usuario alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());
        
        if(alumno == null) alumno = administradorService.buscarAdministradorPorUsername(username.getUsername());
        
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "misInscripciones";
        model.addAttribute("inscripciones", inscripcionService.buscarInscripcionPorAlumnoId(alumno.getId()));
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Les meves inscripcions", username);
    }
}
