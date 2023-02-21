/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno;

import cat.copernic.copernicjobs.geral.utils.CargarPantallaPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author Cole
 */
@Controller
public class veurePerfil {
    
    @GetMapping("/veurePerfil")
    public String inicio(Model model){
        
        ArrayList<String> nombreBotones = new ArrayList<>(Arrays.asList("Inici","Tancar Sessió"));
        String ruta = "alumno/";
        String archivo = "verPerfilAlumno";
        return CargarPantallaPrincipal.cargar(model, nombreBotones, ruta, archivo);
    }
        
}
