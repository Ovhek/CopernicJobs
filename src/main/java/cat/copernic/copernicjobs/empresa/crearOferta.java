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
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Albert
 */
@Controller
public class crearOferta {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    EmpresaService empresaService;

    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa") //Pàgina inicial dels gossos
    public String empresa(Model model) {
        Empresa empresa = new Empresa();
        empresa.setId(3);
        //llistarGossos() retorna el llistat d'objectes gos guardats en la taula gossos de la BBDD    
        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));

        return "crearOferta"; //Retorna la pàgina iniciEnviarDades
    }

    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa/crearoferta")
    public String inicio(@AuthenticationPrincipal UserDetails user,Model model) {
        Oferta oferta = new Oferta();
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "crearoferta";

        model.addAttribute("oferta", oferta);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Crear Oferta", user);
    }

    @PreAuthorize("hasAuthority('Empresa')")
    @PostMapping("/empresa/registraroferta")
    public String registrarOferta(@RequestParam(name = "boton") String btnOferta, @Valid Oferta oferta, Errors errors) {

        if (btnOferta.equals("registrar")) {
            guardarOferta(oferta,errors);
            return "redirect:/misofertas"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
        }else{
            subirPDF();
        }
        
        return "redirect:/crearoferta";
        //else borrarOferta(oferta);

    }
    
    public void subirPDF(){
        
    
    
    }

    public String guardarOferta(@Valid Oferta oferta, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/misofertas";
        } else {
            Empresa e = new Empresa();
            e.setId(3);
            Empresa empresa = empresaService.cercarEmpresa(e);
            oferta.setEmpresa(empresa);
            oferta.setFechaPeticion(LocalDate.now());
            oferta.setFechaValidacion(LocalDate.now());
            oferta.setEnlacePDF("enlacePDF");
            oferta.setInscripciones(new ArrayList<Inscripcion>());
            ofertaService.afegirOferta(oferta); //Afegim el gos passat per paràmetre a la base de dades
            return "redirect:/misofertas";
        }
    }
}
