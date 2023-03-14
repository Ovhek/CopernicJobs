/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cole
 */
@Controller
public class EditarPerfilAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @GetMapping("/editarPerfilAlumne")
    public String inicio(Model model) {
        int id = 1;
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "editarPerfilAlumno";

        Alumno alumno = new Alumno();
        alumno.setId(id);

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Editar Perfil");
    }

    @PostMapping("/editarPerfilAlumne")
    public String editarPerfilAlumno(@RequestParam(name = "button", required = false) String btnValue, Alumno alumno, Model model) {
        //Buscamos el alumno en la BD.
        Alumno alumnoDB = alumnoService.buscarAlumno(alumno);

        //Comprobamos que el valor del botón de acción del post no sea nulo
        if (btnValue != null) {
            
            //Comprobamos que el botón pulsado es "Guardar Canvis"
            if (btnValue.equals("guardar")) {
                
                //Obtenemos los metodos de la entidad alumno.
                Method[] metodos = alumnoDB.getClass().getMethods();
                
                //Iteramos sobre los metodos
                for (Method metodo : metodos) {
                    
                    //Comprobamos que el metodo comienza con "get" --> es un getter.
                    if(metodo.getName().startsWith("get") && metodo.getParameterCount() == 0){
                        try {
                            
                            //Nombre del metodo
                            String nombreMetodo = metodo.getName();
                            
                            //Valor del metodo en la entidad Alumno de la BD
                            Object valueAlumnoDB = metodo.invoke(alumnoDB);
                            
                            //Valor del metodo en la entidad Alumno del Post
                            Object valueAlumnoPost = metodo.invoke(alumno);
                            
                            //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AlumnoDB.
                            if(valueAlumnoPost != null && !nombreMetodo.contains("Class")){
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
            else if (btnValue.equals("baixa")){
                alumnoDB.setBaja(true);
            }
            else return "redirect:/veurePerfilAlumne";
            alumnoService.anadirAlumno(alumnoDB);
        }
        return "redirect:/veurePerfilAlumne";
    }
}
