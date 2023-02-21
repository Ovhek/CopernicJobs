/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador;

import cat.copernic.copernicjobs.geral.utils.CargarPantallaPrincipal;
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
        
        ArrayList<String> nombreBotones = new ArrayList<>(Arrays.asList("Inici","Tancar Sessió"));
        String ruta = "administrador/";
        String archivo = "crearNoticia";
        return CargarPantallaPrincipal.cargar(model, nombreBotones, ruta, archivo);
    }
        
}

