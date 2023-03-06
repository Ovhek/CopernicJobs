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
 *
 * @author Cole
 */
@Controller
public class Login {
    
    @GetMapping("/login")
    public String inicio(){
        return "login";
    }
    
    @PostMapping("/login")
    public String login(String correo, String contrasenya, Model model){
        boolean loginValido = false;
        
        loginValido = correo.equals("alex") && contrasenya.equals("1234");
        
        String redirect = "redirect:";
        
        if(loginValido) redirect += "/inici";
        else {
            model.addAttribute("loginIncorrecto",true);
            redirect += "/login";
        }
        
        return redirect;
        
    }
}
