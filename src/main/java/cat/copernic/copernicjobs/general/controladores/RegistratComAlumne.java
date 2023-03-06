/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.model.Alumno;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Cole
 */
@Controller
public class RegistratComAlumne {
    
    @Autowired
    AlumnoService alumnoService;
    
    @GetMapping("/registratComAlumne")
    public String inicio(Model model){
        Alumno alumno = new Alumno();
        alumno.setFechaRegistro(LocalDate.now());
        model.addAttribute("alumno", new Alumno());
        return "registratComAlumne";
    }
    
    @PostMapping("/registrarAlumne")
    public String registrarAlumne(Alumno alumno, String contrasenyaRepetida){
        String sexoDesc = "";
        switch (alumno.getSexo()) {
            case 1:
                sexoDesc = "Home";
                break;
            case 2:
                sexoDesc = "Dona";
                break;
            case 3:
                sexoDesc = "Altre";
                break;
            case 4:
                sexoDesc = "Prefereixo no dir'ho";
                break;   
            default:
                sexoDesc = "Invalid";
        }
        
        alumno.setSexoDesc(sexoDesc);
        
        alumnoService.anadirAlumno(alumno);
        return "/registratComAlumne";
    }
}
