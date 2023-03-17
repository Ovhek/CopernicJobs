/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
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
 * @author Alex
 */
@Controller
public class EditarPerfilAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/editarPerfil")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        Alumno alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());

        if (alumno == null) {
            return "/error";
        }

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "editarPerfilAlumno";

        model.addAttribute("alumno", alumno);
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Editar Perfil");
    }

    @PreAuthorize("hasAuthority('alumne')")
    @PostMapping("/alumne/editarPerfil")
    public String editarPerfilAlumno(@RequestParam(name = "button", required = false) String btnValue, @Valid Alumno alumno, Errors errores, Model model, @AuthenticationPrincipal UserDetails username, BindingResult result, RedirectAttributes redirectAttributes, String passwordNueva, String confirmaPasswordNueva) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Alumno alumnoDB = alumnoService.buscarAlumno(alumno);

        ObjectError error = null;

        if (alumnoDB == null) {
            error = new ObjectError("UsuarioNoExiste", messageSource.getMessage("error.usuarionoexiste", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (!alumno.getUsername().equals(alumnoDB.getUsername())) {
            Alumno alumnoTemp = alumnoService.buscarAlumnoPorUsername(alumno.getUsername());
            if (alumnoTemp != null) {
                error = new ObjectError("UsuarioYaExiste", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
                result.addError(error);
            }
        }

        //No funciona
        if (alumno.getPassword() != null && alumnoDB.getPassword() != null && !passwordEncoder.matches(alumno.getPassword(), alumnoDB.getPassword())) {
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

                //Obtenemos los metodos de la entidad alumno.
                Method[] metodos = alumnoDB.getClass().getMethods();

                //Iteramos sobre los metodos
                for (Method metodo : metodos) {

                    //Comprobamos que el metodo comienza con "get" --> es un getter.
                    if (metodo.getName().startsWith("get") && metodo.getParameterCount() == 0) {
                        try {

                            //Nombre del metodo
                            String nombreMetodo = metodo.getName();

                            //Valor del metodo en la entidad Alumno de la BD
                            Object valueAlumnoDB = metodo.invoke(alumnoDB);

                            //Valor del metodo en la entidad Alumno del Post
                            Object valueAlumnoPost = null;
                            if (nombreMetodo.equals("getPassword") && !passwordNueva.isBlank()) {
                                valueAlumnoPost = EncriptarContrasenya.encryptar(passwordNueva);
                            } else if (nombreMetodo.equals("getPassword") && passwordNueva.isBlank()) {
                                continue;
                            } else {
                                valueAlumnoPost = metodo.invoke(alumno);
                            }

                            //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AlumnoDB.
                            if (valueAlumnoPost != null && !nombreMetodo.contains("Class")) {
                                String nombreMetodoSetter = nombreMetodo.replace("get", "set");
                                Method metodoSetter = Alumno.class.getMethod(nombreMetodoSetter, metodo.getReturnType());

                                metodoSetter.invoke(alumnoDB, valueAlumnoPost);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else if (btnValue.equals("baixa")) {
                alumnoDB.setBaja(true);
            } else {
                return "redirect:/alumne/veurePerfilAlumne";
            }
            String sexoDesc = "";
            switch (alumno.getSexo()) {
                case 1:
                    sexoDesc = "Home";
                    break;
                case 2:
                    sexoDesc = "Dona";
                    break;
                case 3:
                    sexoDesc = "Altre";
                    break;
                case 4:
                    sexoDesc = "Prefereixo no dir'ho";
                    break;
                default:
                    sexoDesc = "Invalid";
            }
            alumnoDB.setSexoDesc(sexoDesc);
            alumnoService.anadirAlumno(alumnoDB);
        }
        redirectAttributes.addFlashAttribute("CanvisGuardats", messageSource.getMessage("info.alumneguardat", null, Locale.ENGLISH));
        return "redirect:/alumne/veurePerfilAlumne";
    }
}
