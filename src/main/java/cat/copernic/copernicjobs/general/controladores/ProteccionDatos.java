/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador encargado de los endpoints de la página de protección de datos.
 * @author Alex
 */
@Controller
public class ProteccionDatos {
    
    @GetMapping("/protecciodades")
    public String inicio(){
        return "proteccionDatos";
    }
}
