/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.model.Alumno;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Cole
 */
@Controller
public class DonarseDalta {
    
    @Autowired
    AlumnoService alumnoService;
    
    @GetMapping("/donatDalta")
    public String inicio(@AuthenticationPrincipal UserDetails user){
        if(user != null) {
            if (alumnoService.buscarAlumnoPorUsername(user.getUsername()) != null) {
                Alumno alumno = alumnoService.buscarAlumnoPorUsername(user.getUsername());
                alumno.setBaja(false);
                alumnoService.anadirAlumno(alumno);
                return "redirect:/inici";
            }
                
        }
        return "darAlta";
    }
}
