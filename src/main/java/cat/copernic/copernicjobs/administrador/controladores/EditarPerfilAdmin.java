/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import jakarta.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author joang
 */
@Controller
public class EditarPerfilAdmin {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/editarPerfilAdmin")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        Administrador administrador = administradorService.buscarAdministradorPorUsername(username.getUsername());

        if (administrador == null) {
            return "/error";
        }
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "editarPerfilAdmin";

        model.addAttribute("administrador", administrador);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/editarPerfilAdministrador")
    public String editarPerfilAdministrador(@RequestParam(name = "button", required = false) String btnValue, @Valid Administrador administrador, Errors errores, Model model, @AuthenticationPrincipal UserDetails username, BindingResult result, RedirectAttributes redirectAttributes, String passwordNueva, String confirmaPasswordNueva) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Administrador administradorDB = administradorService.buscarAdministrador(administrador);

        ObjectError error = null;

        if (administradorDB == null) {
            error = new ObjectError("UsuarioNoExiste", messageSource.getMessage("error.usuarionoexiste", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (!administrador.getUsername().equals(administradorDB.getUsername())) {
            Administrador administradorTemp = administradorService.buscarAdministradorPorUsername(administrador.getUsername());
            if (administradorTemp != null) {
                error = new ObjectError("UsuarioYaExiste", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
                result.addError(error);
            }
        }

        //No funciona
        if (administrador.getPassword() != null && administradorDB.getPassword() != null && !passwordEncoder.matches(administrador.getPassword(), administradorDB.getPassword())) {
            error = new ObjectError("ContraseñaActualNoCoincide", messageSource.getMessage("error.editarusuariocontrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (!passwordNueva.equals(confirmaPasswordNueva)) {
            error = new ObjectError("ContraseñaNoCoincide", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...
            List<String> erroresString = new ArrayList<>();
            errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

            List<String> erroresBindingString = new ArrayList<>();
            result.getAllErrors().forEach(err -> erroresBindingString.add(err.getDefaultMessage()));

            model.addAttribute("errores", erroresString);
            model.addAttribute("errores", erroresBindingString);
            return inicio(model, username);
        }

        //Comprobamos que el valor del botón de acción del post no sea nulo
        if (btnValue != null) {

            //Comprobamos que el botón pulsado es "Guardar Canvis"
            if (btnValue.equals("guardar")) {

                //Obtenemos los metodos de la entidad administrador.
                Method[] metodos = administradorDB.getClass().getMethods();

                //Iteramos sobre los metodos
                for (Method metodo : metodos) {

                    //Comprobamos que el metodo comienza con "get" --> es un getter.
                    if (metodo.getName().startsWith("get") && metodo.getParameterCount() == 0) {
                        try {

                            //Nombre del metodo
                            String nombreMetodo = metodo.getName();

                            //Valor del metodo en la entidad Administrador de la BD
                            Object valueAdministradorDB = metodo.invoke(administradorDB);

                            //Valor del metodo en la entidad Administrador del Post
                            Object valueAdministradorPost = null;
                            if (nombreMetodo.equals("getPassword") && !passwordNueva.isBlank()) {
                                valueAdministradorPost = EncriptarContrasenya.encryptar(passwordNueva);
                            } else if (nombreMetodo.equals("getPassword") && passwordNueva.isBlank()) {
                                continue;
                            } else {
                                valueAdministradorPost = metodo.invoke(administrador);
                            }

                            //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AdministradorDB.
                            if (valueAdministradorPost != null && !nombreMetodo.contains("Class")) {
                                String nombreMetodoSetter = nombreMetodo.replace("get", "set");
                                Method metodoSetter = Administrador.class.getMethod(nombreMetodoSetter, metodo.getReturnType());

                                metodoSetter.invoke(administradorDB, valueAdministradorPost);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                return "redirect:/verPerfilAdmin";
            }
            String sexoDesc = "";
            
            administradorService.anadirAdministrador(administradorDB);
        }
        redirectAttributes.addFlashAttribute("CanvisGuardats", messageSource.getMessage("info.alumneguardat", null, Locale.ENGLISH));
        return "redirect:/verPerfilAdmin";
    }

}
