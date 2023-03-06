/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.DAO.EmpresaDAO;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
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
public class ValidarRegistreEmpresa {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private EmpresaDAO empresaDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @GetMapping("/validarRegistreEmpresa")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "validarRegistreEmpresa";
        
        Empresa empresa = (Empresa) empresaDAO.findById(3).get();
        
        HashMap<String, Object> datos = new HashMap<>(){{
            put("emNombre", empresa.getNombreEmpresa());
            put("emDesc", empresa.getDescripcionEmpresa());
            put("emDir", empresa.getDireccion());
            put("emPost", empresa.getCodPostal());
            put("emMun", empresa.getMunicipio());
            put("emWeb", empresa.getWebEmpresa());            
            put("resNombre", empresa.getNombre());
            put("resApellidos", empresa.getApellidos());
            put("resMob", empresa.getMovil());

        }};
        model.addAllAttributes(datos); 
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}