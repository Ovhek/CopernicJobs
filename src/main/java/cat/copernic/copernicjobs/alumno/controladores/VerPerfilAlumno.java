/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author Cole
 */
@Controller
public class VerPerfilAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/veurePerfil")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {
        int id = alumnoService.buscarAlumnoPorUsername(username.getUsername()).getId();
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "verPerfilAlumno";

        Alumno alumno = new Alumno();
        alumno.setId(id);
       
        
        model.addAttribute("alumno",alumnoService.buscarAlumno(alumno));
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Perfil",username);
    }

}
