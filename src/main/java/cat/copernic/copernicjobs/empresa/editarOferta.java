/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
public class editarOferta {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    EmpresaService empresaService;

    /**
     *
     * Método que muestra la página de edición de una oferta de trabajo para una
     * empresa. Requiere que el usuario tenga el rol de "Empresa" y recibe como
     * parámetros los detalles del usuario autenticado, el objeto Oferta a
     * editar y un objeto Model para pasar datos a la vista.
     *
     * @param user Detalles del usuario autenticado.
     * @param oferta Objeto Oferta a editar.
     * @param model Objeto Model para pasar datos a la vista.
     * @return La vista correspondiente a la página de edición de la oferta de
     * trabajo.
     */
    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa/editaroferta/{id}")
    public String editar(@AuthenticationPrincipal UserDetails user, Oferta oferta, Model model) {
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "editaroferta";
        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Editar Oferta", user);
    }

    /**
     *
     * Método que maneja la acción realizada por el usuario sobre una oferta de
     * trabajo.
     *
     * Requiere que el usuario tenga el rol de "Empresa" y recibe como
     * parámetros el botón presionado por el usuario y el objeto Oferta a
     * manejar.
     *
     * @param btnOferta Botón presionado por el usuario (guardar o borrar).
     *
     * @param oferta Objeto Oferta a manejar.
     *
     * @return Redirección a la página inicial de la empresa.
     */
    @PreAuthorize("hasAuthority('Empresa')")
    @PostMapping("/empresa/manejaroferta")
    public String manejarOferta(@RequestParam(name = "boton") String btnOferta, Oferta oferta) {

        if (btnOferta.equals("guardar")) {
            guardarOferta(oferta);
        } else {
            borrarOferta(oferta);
        }

        return "redirect:inici"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    /**
     * Guarda una oferta en la base de datos. Si ya existe una oferta con el
     * mismo identificador, la función actualizará sus datos.
     *
     * @param oferta la oferta a guardar o actualizar.
     */
    public void guardarOferta(Oferta oferta) {
        Oferta old = ofertaService.cercarOferta(oferta);
        old.setTituloOferta(oferta.getTituloOferta());
        old.setDescripcionOferta(oferta.getDescripcionOferta());
        old.setRequisitosAlumno(oferta.getRequisitosAlumno());
        old.setSeOfrece(oferta.getSeOfrece());
        ofertaService.afegirOferta(old); //Afegim el gos passat per paràmetre a la base de dades
    }

    /**
     * Elimina una oferta de la base de datos y redirige al usuario a la página
     * de inicio.
     *
     * @param oferta el objeto de tipo Oferta que se eliminará de la base de
     * datos
     * @return una cadena de texto que indica la redirección a la página de
     * inicio
     */
    public String borrarOferta(Oferta oferta) {
        Oferta ofertaDel = ofertaService.cercarOferta(oferta);
        ofertaService.eliminarOferta(ofertaDel); //Afegim el gos passat per paràmetre a la base de dades
        return "redirect:inici";
    }
}
