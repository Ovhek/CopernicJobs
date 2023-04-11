/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.model.Alumno;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para darse de Alta
 * @author Alex
 */
@Controller
public class DonarseDalta {

    /**
     *
     * Método para manejar la solicitud GET de la página de alta de un usuario.
     *
     * @return una cadena que indica la vista a la que se debe redirigir la
     * respuesta
     */
    @GetMapping("/donatDalta")
    public String inicio() {
        return "darAlta";
    }
}
