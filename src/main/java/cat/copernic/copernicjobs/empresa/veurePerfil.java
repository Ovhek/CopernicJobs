/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author Cole
 */
@Controller
public class veurePerfil {
    
    @Autowired
    EmpresaService empresaService;
    
    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa/veurePerfil")
    public String inicio(@AuthenticationPrincipal UserDetails user,Model model){
        int id = empresaService.buscarPorUsername(user.getUsername()).getId();
        
        
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "verperfilempresa";
        Empresa empresa = new Empresa();
        empresa.setId(id);
        
        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Veure Perfil", user);
    }
    
    
}
