/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
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
public class RegistrarAdministrador {

    @Autowired
    AdministradorService administradorService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registrarAdministrador")
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarAdministrador";

        Administrador administrador = new Administrador();
        model.addAttribute("administrador", new Administrador());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PostMapping("/registreAdministrador")
    public String registrarAdministrador(@Valid Administrador administrador, Errors errores, BindingResult result, String contrasenyaRepetida, Model model) {

        if (!administrador.getPassword().equals(contrasenyaRepetida)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (administradorService.buscarAdministradorPorUsername(administrador.getUsername()) != null) {
            ObjectError error = new ObjectError("Username", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
            result.addError(error);
        }
        
        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "registrarAdministrador";

            return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
        }

        if (result.hasErrors()) {
            return "redirect:/registrarAdministrador";
        }

        String sexoDesc = "";

        administrador.setFechaRegistro(LocalDate.now());
        administrador.setPassword(EncriptarContrasenya.encryptar(administrador.getPassword()));
        administradorService.anadirAdministrador(administrador);
        
        model.addAttribute("registrador",true);
        
        return "redirect:/registrarUsuaris";
    }
}
