/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * Controlador para la funcionalidad de registrar usuarios en el sistema.
 * Requiere autorización de tipo 'administrador' para acceder a las rutas
 * asociadas. Este controlador maneja las solicitudes GET para la página de
 * inicio de registro de usuarios. Carga un archivo HTML y lo añade a la
 * plantilla de la página principal.
 *
 * @author joang
 */
@Controller
public class RegistrarUsuaris {

    /**
     * Maneja las solicitudes GET para la página de inicio de registro de
     * usuarios. Carga un archivo HTML y lo añade a la plantilla de la página
     * principal.
     *
     * @param model El modelo utilizado para la vista.
     * @param username Los detalles del usuario autenticado.
     * @return El nombre de la vista a cargar.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/registrarUsuaris")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarUsuaris";

        model.addAttribute("test", "vivimos");
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

}
