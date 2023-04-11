/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import jakarta.validation.Valid;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Clase controladora que maneja las operaciones relacionadas con la
 * visualización y edición de alumnos. Esta clase utiliza el servicio
 * AlumnoService para interactuar con la base de datos y realizar operaciones
 * CRUD en la entidad Alumno. Requiere autorización de usuario con el rol
 * 'administrador' para acceder a las funciones.
 *
 * @author joang
 */
@Controller
public class VerAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    /**
     * Método que maneja una petición POST en la ruta "/guardarAlumno". Este
     * método permite guardar cambios en un objeto Alumno en la base de datos.
     * Requiere autorización de usuario con el rol 'administrador'.
     *
     * @param btnValue Valor del botón de acción del formulario
     * @param alumno Objeto Alumno con los datos modificados
     * @param errores Objeto Errors que contiene los errores de validación del
     * formulario
     * @param model Objeto Model para agregar atributos a la vista
     * @param result Objeto BindingResult que contiene los errores de validación
     * del formulario
     * @param redirectAttributes Objeto RedirectAttributes para agregar
     * atributos a la redirección
     * @param username Objeto UserDetails que contiene los detalles del usuario
     * autenticado
     * @return String con el nombre de la vista a la que se redirige
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/guardarAlumno") //action=guardarAlumno
    public String guardarAlumno(@RequestParam(name = "button", required = false) String btnValue, @Valid Alumno alumno, Errors errores, Model model, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails username) {
        //Buscamos el alumno en la BD.
        Alumno alumnoDB = alumnoService.buscarAlumno(alumno);

        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...
            List<String> erroresString = new ArrayList<>();
            errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

            List<String> erroresBindingString = new ArrayList<>();
            result.getAllErrors().forEach(err -> erroresBindingString.add(err.getDefaultMessage()));

            model.addAttribute("errores", erroresString);
            model.addAttribute("errores", erroresBindingString);

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "editarAlumno";

            return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
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
                            Object valueAlumnoPost = metodo.invoke(alumno);

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
            }
            alumnoService.anadirAlumno(alumnoDB);
            if (btnValue.equals("bajar")) {
                alumno.setBaja(true);
                alumnoService.anadirAlumno(alumno);
            }
        }

        return "redirect:/verAlumnos"; //Retornem a la pàgina alumne mitjançant redirect
    }

    /**
     * Método que maneja una petición GET en la ruta "/editarAlumno/{id}". Este
     * método permite visualizar y editar los datos de un alumno en la vista
     * correspondiente. Requiere autorización de usuario con el rol
     * 'administrador'.
     *
     * @param alumno Objeto Alumno con los datos del alumno a editar
     * @param model Objeto Model para agregar atributos a la vista
     * @param username Objeto UserDetails que contiene los detalles del usuario
     * autenticado
     * @return String con el nombre de la vista a la que se redirige
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/editarAlumno/{id}")
    public String editar(Alumno alumno, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "editarAlumno";

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

}
