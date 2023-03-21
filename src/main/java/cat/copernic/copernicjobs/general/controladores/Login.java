/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Cole
 */
@Controller
public class Login {

    @GetMapping("/")
    public String login(@AuthenticationPrincipal UserDetails user) {
        if(user != null) return "redirect:/inici";
        return "login";
    }
    
    @GetMapping("/login")
    public String login_(@AuthenticationPrincipal UserDetails user) {
        if(user != null) return "redirect:/inici";
        return "login";
    }
}
