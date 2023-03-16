/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author joang
 */
@Controller
public class VerOfertaAdmin {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private OfertaService ofertaService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO
    
    @PostMapping("/guardarOferta")
    public String guardarOferta(@RequestParam(name = "boton")String btnOferta,Oferta oferta){
        if(btnOferta.equals("guardar")) guardar(oferta);
        else borrar(oferta);
        
        return "redirect:/verOfertasAdmin";
    }
    
    public void guardar(Oferta oferta){
        ofertaService.afegirOferta(oferta);;
    }
    
    public String borrar(Oferta oferta){
        ofertaService.eliminarOferta(oferta);
        return "redirect:/verOfertasAdmin";
    }
    
    @GetMapping("/editarOferta/{id}")
    public String editar(Oferta oferta, Model model) {
        
        String ruta = "administrador/";
        
        String archivo = "editarOferta";
        
        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));
        
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}
