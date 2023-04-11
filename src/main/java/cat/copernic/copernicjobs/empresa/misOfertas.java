/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Albert
 */
@Controller
public class misOfertas {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private OfertaService ofertaService;
    
    @Autowired
    EmpresaService empresaService;

    @PreAuthorize("hasAuthority('empresa')")
    @GetMapping("/empresa/inici")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails user) {
        
        Empresa empresa = empresaService.buscarPorUsername(user.getUsername());
        
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "misofertas";
       
        if(empresa != null) model.addAttribute("ofertas", ofertaService.listarPorNombre(empresa.getNombreEmpresa()));
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Les meves ofertes", user);
    }
    
    @PostMapping("/empresa/buscaroferta")
    public String buscarOferta(@RequestParam(name = "buscar") String btnValue,@RequestParam(name = "search-input") String buscar,@RequestParam(name="sort-select") String ordenar, @AuthenticationPrincipal UserDetails user,Model model){
        int id = empresaService.buscarPorUsername(user.getUsername()).getId();
        Empresa empresa = new Empresa();
        empresa.setId(id);
        Empresa emp = empresaService.cercarEmpresa(empresa);
        
        
        
        //if((buscar!=null)&&(ordenar!="0")){
        model.addAttribute("ofertas",ofertaService.filtrarOfertasOrdenacion(buscar, ordenar, emp.getNombreEmpresa()));
        
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "misofertas";
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Les meves ofertes", user);
    }

    
}
