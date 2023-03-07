/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Albert
 */
@Controller
public class editarPerfil {
        
    @Autowired
    EmpresaService empresaService;
    
    @GetMapping("/editarperfilempresa/{id}")
    public String inicio(Model model,Empresa empresa){
        model.addAttribute("empresa",empresaService.cercarEmpresa(empresa));
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "editarperfilempresa";
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo);
    }
    
    @PostMapping("/guardarperfil")
    public String guardar(Model model,Empresa empresa){
        empresaService.afegirEmpresa(empresa);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return "redirect:misofertas";
                
    }
    
    
    
    

}
