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

    /**
     *
     * Método que se encarga de mostrar la página de inicio para una empresa,
     * con un listado de las ofertas publicadas por la empresa.
     *
     * @param model el objeto Model que se utiliza para pasar datos a la vista
     *
     * @param user el objeto UserDetails que contiene los datos del usuario
     * autenticado
     *
     * @return una cadena de texto que representa la ruta del archivo html a
     * cargar
     */
    @PreAuthorize("hasAuthority('empresa')")
    @GetMapping("/empresa/inici")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails user) {

        Empresa empresa = empresaService.buscarPorUsername(user.getUsername());

        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "misofertas";

        if (empresa != null) {
            model.addAttribute("ofertas", ofertaService.listarPorNombre(empresa.getNombreEmpresa()));
        }

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Les meves ofertes", user);
    }

    /**
     *
     * Método que procesa la petición POST de buscar oferta de una empresa en el
     * sistema.
     *
     * @param btnValue El valor del botón que se presionó para realizar la
     * búsqueda de la oferta.
     * @param buscar El texto a buscar dentro de las ofertas.
     * @param ordenar El orden en el que se deben mostrar las ofertas.
     * @param user El objeto UserDetails que representa al usuario que está
     * realizando la búsqueda.
     * @param model El objeto Model utilizado para almacenar y pasar datos a la
     * vista.
     * @return La plantilla HTML de la página principal de la empresa con la
     * lista de ofertas filtradas y ordenadas.
     */
    @PostMapping("/empresa/buscaroferta")
    public String buscarOferta(@RequestParam(name = "buscar") String btnValue, @RequestParam(name = "search-input") String buscar, @RequestParam(name = "sort-select") String ordenar, @AuthenticationPrincipal UserDetails user, Model model) {
        int id = empresaService.buscarPorUsername(user.getUsername()).getId();
        Empresa empresa = new Empresa();
        empresa.setId(id);
        Empresa emp = empresaService.cercarEmpresa(empresa);

        //if((buscar!=null)&&(ordenar!="0")){
        model.addAttribute("ofertas", ofertaService.filtrarOfertasOrdenacion(buscar, ordenar, emp.getNombreEmpresa()));

        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "misofertas";

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Les meves ofertes", user);
    }

}
