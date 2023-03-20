/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    public String registrarAdministrador(@Valid Administrador administrador, String contrasenyaRepetida, Errors errors, Model model) {
        if (errors.hasErrors()) { //Si s'han produït errors...

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "registrarAdministrador";
            
            return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
        }

        String sexoDesc = "";

        administrador.setFechaRegistro(LocalDate.now());

        administradorService.anadirAdministrador(administrador);
        return "redirect:/registrarUsuaris";
    }
}
