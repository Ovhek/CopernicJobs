/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de los endpoints de recuperar contraseña.
 * @author Alex
 */
@Controller
public class RecuperarContrasenya {
    @GetMapping("/recuperarContrasenya")
    public String inicio(){
        return "recuperarContrasenya";
    }
}
