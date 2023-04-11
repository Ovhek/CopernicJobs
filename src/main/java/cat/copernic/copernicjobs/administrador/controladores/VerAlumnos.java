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
 * Controlador para la funcionalidad de ver alumnos en el sistema. Este
 * controlador maneja las solicitudes HTTP relacionadas con la visualización de
 * alumnos, incluyendo la página principal para listar alumnos y la página para
 * ver los detalles de un alumno específico. Se requiere que el usuario tenga el
 * rol de 'administrador' para acceder a estas funcionalidades.
 *
 * @author joang
 */
@Controller
public class VerAlumnos {

    @Autowired
    private AlumnoService alumnoService;

    /**
     * Maneja las solicitudes GET en la ruta '/verAlumnos' para mostrar la
     * página principal de listado de alumnos.
     *
     * @param model El modelo utilizado para pasar datos a la vista.
     * @param username Detalles de autenticación del usuario actual.
     * @return El nombre de la plantilla de la página principal de listado de
     * alumnos.
     */
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

    /**
     * Maneja las solicitudes GET en la ruta '/verAlumno/{id}' para mostrar la
     * página de detalles de un alumno específico.
     *
     * @param alumno Objeto Alumno utilizado para obtener el ID del alumno cuyos
     * detalles se desean ver.
     * @param model El modelo utilizado para pasar datos a la vista.
     * @param username Detalles de autenticación del usuario actual.
     * @return El nombre de la plantilla de la página de detalles de un alumno.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verAlumno/{id}")
    public String ver(Alumno alumno, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verAlumno";

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);

    }
}
