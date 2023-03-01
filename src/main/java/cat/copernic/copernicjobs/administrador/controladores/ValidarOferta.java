/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.DAO.OfertaDAO;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Oferta;
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
public class ValidarOferta {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private OfertaDAO ofertaDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @GetMapping("/validarOferta")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "validarOferta";
        
        Oferta oferta = (Oferta) ofertaDAO.findById(1).get();        
        
        HashMap<String, Object> datos = new HashMap<>(){{
            put("ofTit", oferta.getTituloOferta());
            put("nomEmpresa", oferta.getEmpresa().getNombreEmpresa());
            put("ofDesc", oferta.getDescripcionOferta());
            put("ofReq", oferta.getRequisitosAlumno());
            put("ofOfer", oferta.getSeOfrece());
            put("alumnesInscrits", 10);
            put("alumnoNoInscrito", true);
            put("ofFecha", oferta.getFechaValidacion());
        }};
        model.addAllAttributes(datos);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}