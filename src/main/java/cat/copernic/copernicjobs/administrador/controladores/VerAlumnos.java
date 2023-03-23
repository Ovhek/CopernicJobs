/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
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
public class VerAlumnos {

    @Autowired
    private AlumnoService alumnoService;

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verAlumnos")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verAlumnos";

        model.addAttribute("alumnos", alumnoService.listarAlumnos());
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verAlumno/{id}")
    public String ver(Alumno alumno, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verAlumno";

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);

    }
}
