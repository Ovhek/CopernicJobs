/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
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
 * @author joang
 */
@Controller
public class RegistrarAlumne {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registrarAlumne")
    public String inicio(Model model) {
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarAlumne";

        Alumno alumno = new Alumno();
        model.addAttribute("alumno", new Alumno());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PostMapping("/registreAlumne")
    public String registrarAlumne(@Valid Alumno alumno, Errors errores, BindingResult result, String contrasenyaRepetida, Model model) {

        if (!alumno.getPassword().equals(contrasenyaRepetida)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (alumnoService.buscarAlumnoPorUsername(alumno.getUsername()) != null) {
            ObjectError error = new ObjectError("Username", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
            result.addError(error);
        }
        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "registrarAlumne";

            return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
        }

        if (result.hasErrors()) {
            return "redirect:/registrarAlumne";
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


        model.addAttribute("registrado",true);

        return "redirect:/registrarUsuaris";
    }
}
