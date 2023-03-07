/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.DAO.OfertaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Albert
 */
@Controller
public class editarOferta{
    
    @Autowired
    OfertaService ofertaService;
    
    @Autowired
    EmpresaService empresaService;  
    
    @GetMapping("/editaroferta/{id}")
    public String editar(Oferta oferta, Model model) {
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "editaroferta";
        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo);
    }
    
    @PostMapping("/manejaroferta")
    public String manejarOferta(@RequestParam(name = "boton")String btnOferta,Oferta oferta) {
        
        if(btnOferta.equals("guardar")) guardarOferta(oferta);
        else borrarOferta(oferta);

        return "redirect:/misofertas"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    public void guardarOferta(Oferta oferta) {
        Oferta old = ofertaService.cercarOferta(oferta);
        old.setTituloOferta(oferta.getTituloOferta());
        old.setDescripcionOferta(oferta.getDescripcionOferta());
        old.setRequisitosAlumno(oferta.getRequisitosAlumno());
        old.setSeOfrece(oferta.getSeOfrece());
        ofertaService.afegirOferta(old); //Afegim el gos passat per paràmetre a la base de dades
    }
    
    public String borrarOferta(Oferta oferta) {
        Oferta o = ofertaService.cercarOferta(oferta);
        ofertaService.eliminarOferta(o); //Afegim el gos passat per paràmetre a la base de dades
        return "redirect:/misofertas";
    }
}
