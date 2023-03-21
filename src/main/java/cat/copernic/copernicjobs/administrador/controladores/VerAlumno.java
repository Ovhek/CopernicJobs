/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import jakarta.validation.Valid;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author joang
 */
@Controller
public class VerAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @PostMapping("/guardarAlumno") //action=guardarAlumno
    public String guardarAlumno(@RequestParam(name = "button", required = false) String btnValue, @Valid Alumno alumno, Errors errores, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
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

            return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
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
            if(btnValue.equals("bajar")){
                alumno.setBaja(true);
                alumnoService.anadirAlumno(alumno);
            }
        }

        return "redirect:/verAlumnos"; //Retornem a la pàgina alumne mitjançant redirect
    }

    @GetMapping("/editarAlumno/{id}")
    public String editar(Alumno alumno, Model model) {

        String ruta = "administrador/";

        String archivo = "editarAlumno";

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));

        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

}
