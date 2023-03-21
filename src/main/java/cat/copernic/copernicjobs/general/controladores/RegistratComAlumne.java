/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.model.Alumno;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
    
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/registratComAlumne")
    public String inicio(Model model){
        Alumno alumno = new Alumno();
        
        model.addAttribute("alumno", new Alumno());
        return "registratComAlumne";
    }

    @PostMapping("/registratComAlumne")
    public String registrarAlumne(@Valid Alumno alumno, Errors errores, BindingResult result, Model model, String contrasenyaRepetida) {

        if (!alumno.getPassword().equals(contrasenyaRepetida)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (alumnoService.buscarAlumnoPorUsername(alumno.getUsername()) != null) {
            ObjectError error = new ObjectError("Username", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
            result.addError(error);
        }
        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            return "/registratComAlumne"; //Mostrem la pàgina del formulari
        }

        if (result.hasErrors()) {
            return "/registratComAlumne";
        }
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
        alumno.setFechaRegistro(LocalDate.now());
        alumno.setPassword(EncriptarContrasenya.encryptar(alumno.getPassword()));
        alumnoService.anadirAlumno(alumno);
        return "/registratComAlumne";
    }
}
