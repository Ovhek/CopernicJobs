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

        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "editarperfilempresa";
        
        model.addAttribute("empresa",empresaService.cercarEmpresa(empresa));
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo);
    }
    
    @PostMapping("/guardarcambios")
    public String guardar(Empresa empresa){
        //Cargamos la empresa a editar.
        Empresa empresaEdit= empresaService.cercarEmpresa(empresa);
        //Modificamos sus valores con los que entran a traves del metodo POST.
        empresaEdit.setCodPostal(empresa.getCodPostal());
        empresaEdit.setNombreEmpresa(empresa.getNombreEmpresa());
        empresaEdit.setDescripcionEmpresa(empresa.getDescripcionEmpresa());
        empresaEdit.setMunicipio(empresa.getMunicipio());
        empresaEdit.setMovilEmpresa(empresa.getMovilEmpresa());
        empresaEdit.setWebEmpresa(empresa.getWebEmpresa());
        //Aqui modificamos los datos del Responsable, que han sido actualizados por el metodo POST.
        empresaEdit.setNombre(empresa.getNombre());
        empresaEdit.setApellidos(empresa.getApellidos());
        empresaEdit.setMovil(empresa.getMovil());
        empresaEdit.setDireccion(empresa.getDireccion());
        //Una vez actualizamos los datos, al añadir la empresa sobreescribira la actual.
        empresaService.afegirEmpresa(empresaEdit);
        //Una vez hecho esto, redireccionamos a mis ofertas.
        return "redirect:verperfilempresa";
                
    }
    
    
    
    

}
