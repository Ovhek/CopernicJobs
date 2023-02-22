/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class CrearNoticia {
    
    @GetMapping("/crearNoticia")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "crearNoticia";
        
        model.addAttribute("test","vivimos");
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}

