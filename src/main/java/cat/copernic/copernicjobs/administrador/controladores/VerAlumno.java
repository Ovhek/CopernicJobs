/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.DAO.AlumnoDAO;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoDAO alumndoDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @GetMapping("/verAlumno")
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verAlumno";
        
        Alumno alumno = new Alumno();
        alumno.setNombre("John");
        alumno.setApellidos("Doe");
        alumno.setUsername("JohnDoe@gmail.com");
        alumno.setDireccion("Carrer vinyals 07");
        alumno.setMovil("613157834");
        alumno.setSexo(1);
        alumno.setDni("56899047L");
        alumno.setTarjetaSanitaria("4563324");
        alumno.setSegSocial("123456789");
        
        
        HashMap dummyData = new HashMap<String,Object>(){{
            put("nombreAlumno", alumno.getNombre());
            put("apellidosAlumno", alumno.getApellidos());
            put("correoAlumno", alumno.getUsername());
            put("direccionAlumno", alumno.getDireccion());
            put("movilAlumno", alumno.getMovil());
            put("generoAlumno", alumno.getSexo());            
            put("dniAlumno", alumno.getDni());            
            put("tsAlumno", alumno.getTarjetaSanitaria());            
            put("socialAlumno", alumno.getSegSocial());            
        }};
        
        model.addAllAttributes(dummyData);
        model.addAttribute(ruta);
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

}
