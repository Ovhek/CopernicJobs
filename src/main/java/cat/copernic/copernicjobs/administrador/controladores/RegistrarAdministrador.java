/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador para el registro de administradores en la aplicación.
 *
 * Esta clase maneja las solicitudes HTTP relacionadas con el registro de
 * administradores, incluyendo la validación de datos de entrada, la
 * comprobación de autorización y la gestión de errores. Utiliza anotaciones de
 * Spring como @Controller, @Autowired, @PreAuthorize,
 *
 * @GetMapping y @PostMapping para configurar y gestionar las rutas y acciones
 * del controlador.
 *
 * @author joang
 */
@Controller
public class RegistrarAdministrador {

    @Autowired
    AdministradorService administradorService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Maneja las solicitudes GET a la ruta "/registrarAdministrador".
     *
     * @param model Modelo de datos para la vista.
     * @param username Detalles del usuario autenticado.
     * @return La vista de registro de administrador con los datos necesarios
     * cargados.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/registrarAdministrador")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarAdministrador";

        Administrador administrador = new Administrador();
        model.addAttribute("administrador", new Administrador());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    /**
     * Maneja las solicitudes POST a la ruta "/registreAdministrador".
     *
     * @param administrador Objeto Administrador con los datos del formulario de
     * registro.
     * @param errores Errores de validación del formulario.
     * @param result Resultado de la validación del formulario.
     * @param contrasenyaRepetida Contraseña repetida del administrador.
     * @param model Modelo de datos para la vista.
     * @param username Detalles del usuario autenticado.
     * @return Redirige a la vista de registro de usuarios o muestra errores en
     * caso de que los haya.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/registreAdministrador")
    public String registrarAdministrador(@Valid Administrador administrador, Errors errores, BindingResult result, String contrasenyaRepetida, Model model, @AuthenticationPrincipal UserDetails username) {

        if (!administrador.getPassword().equals(contrasenyaRepetida)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (administradorService.buscarAdministradorPorUsername(administrador.getUsername()) != null) {
            ObjectError error = new ObjectError("Username", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "registrarAdministrador";

            return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
        }

        if (result.hasErrors()) {
            return "redirect:/registrarAdministrador";
        }

        String sexoDesc = "";

        administrador.setFechaRegistro(LocalDate.now());
        administrador.setPassword(EncriptarContrasenya.encryptar(administrador.getPassword()));
        administradorService.anadirAdministrador(administrador);

        model.addAttribute("registrador", true);

        return "redirect:/registrarUsuaris";
    }
}
