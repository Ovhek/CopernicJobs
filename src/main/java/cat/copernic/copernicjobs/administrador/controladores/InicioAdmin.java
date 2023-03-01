/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.DAO.AlumnoDAO;
import cat.copernic.copernicjobs.DAO.EmpresaDAO;
import cat.copernic.copernicjobs.DAO.NoticiaDAO;
import cat.copernic.copernicjobs.DAO.OfertaDAO;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Oferta;
import cat.copernic.copernicjobs.model.Persona;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class InicioAdmin {
    
    @Autowired
    private NoticiaDAO noticiaDAO;
    
    @Autowired 
    private AlumnoDAO alumnoDAO;
    
    @Autowired
    private EmpresaDAO empresaDAO;
    
    @GetMapping("/inicioAdmin")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "inicioAdmin";

        ArrayList<Persona> personas = new ArrayList<>(Arrays.asList(
                new Persona(),
                new Persona()
        ));
        personas.forEach(e -> {
            e.setNombre("Empresa nova");
        });

        model.addAttribute("anuncios", noticiaDAO.findAll());
        model.addAttribute("validaciones", alumnoDAO.findAll());
        model.addAttribute("validaciones", empresaDAO.findAll());
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}

