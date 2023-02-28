/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Albert
 */
public class veureOferta {
    @Autowired
    private OfertaDAO ofertaDao;
    
    @GetMapping("/veureOferta")
    public String inicio(Model model){
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "veureOferta";
        
        Oferta oferta = (Oferta)ofertaDao.findById(1).get();
        
        model.addAttribute("oferta", oferta);
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo);
    } 
}
