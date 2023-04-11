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
 * Controlador del Login
 *
 * @author Alex
 */
@Controller
public class Login {

    /**
     *
     * Método para manejar la solicitud GET de la página de inicio de sesión.
     *
     * @param user objeto UserDetails de Spring Security que representa al
     * usuario autenticado actualmente
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta
     */
    @GetMapping("/")
    public String login(@AuthenticationPrincipal UserDetails user) {
        if (user != null) {
            return "redirect:/inici";
        }
        return "login";
    }

    @GetMapping("/login")
    public String login_(@AuthenticationPrincipal UserDetails user) {
        if (user != null) {
            return "redirect:/inici";
        }
        return "login";
    }

    /**
     *
     * Método para manejar la solicitud GET de la página de cierre de sesión.
     *
     * @param user objeto UserDetails de Spring Security que representa al
     * usuario autenticado actualmente
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta después del cierre de sesión
     */
    @GetMapping("/logout")
    public String logout(@AuthenticationPrincipal UserDetails user) {
        user = null;
        return "login";
    }
}
