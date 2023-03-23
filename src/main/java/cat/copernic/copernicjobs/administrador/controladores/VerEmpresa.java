/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import jakarta.validation.Valid;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author joang
 */
@Controller
public class VerEmpresa {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private EmpresaService empresaService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/guardarEmpresa") //action=guardarEmpresa
    public String guardarEmpresa(@RequestParam(name = "button", required = false) String btnValue, @Valid Empresa empresa, Errors errores, Model model, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails username) {
        //Buscamos la empresa en la BD.
        Empresa empresaDB = empresaService.cercarEmpresa(empresa);

        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...
            List<String> erroresString = new ArrayList<>();
            errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

            List<String> erroresBindingString = new ArrayList<>();
            result.getAllErrors().forEach(err -> erroresBindingString.add(err.getDefaultMessage()));

            model.addAttribute("errores", erroresString);
            model.addAttribute("errores", erroresBindingString);

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "editarEmpresa";

            return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
        }

        //Comprobamos que el valor del botón de acción del post no sea nulo
        if (btnValue != null) {

            //Comprobamos que el botón pulsado es "Guardar Canvis"
            if (btnValue.equals("guardar")) {

                //Obtenemos los metodos de la entidad alumno.
                Method[] metodos = empresaDB.getClass().getMethods();

                //Iteramos sobre los metodos
                for (Method metodo : metodos) {

                    //Comprobamos que el metodo comienza con "get" --> es un getter.
                    if (metodo.getName().startsWith("get") && metodo.getParameterCount() == 0) {
                        try {

                            //Nombre del metodo
                            String nombreMetodo = metodo.getName();

                            //Valor del metodo en la entidad Alumno de la BD
                            Object valueEmpresaDB = metodo.invoke(empresaDB);

                            //Valor del metodo en la entidad Alumno del Post
                            Object valueEmpresaPost = metodo.invoke(empresa);

                            //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AlumnoDB.
                            if (valueEmpresaPost != null && !nombreMetodo.contains("Class")) {
                                String nombreMetodoSetter = nombreMetodo.replace("get", "set");
                                Method metodoSetter = Empresa.class.getMethod(nombreMetodoSetter, metodo.getReturnType());

                                metodoSetter.invoke(empresaDB, valueEmpresaPost);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            empresaService.afegirEmpresa(empresaDB);
            if (btnValue.equals("bajar")) {
                empresa.setBaja(true);
                empresaService.afegirEmpresa(empresa);
            }
        }
        return "redirect:/verEmpresas"; //Retornem a la pàgina alumne mitjançant redirect
    }

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/editarEmpresa/{id}")
    public String editar(Empresa empresa, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "editarEmpresa";

        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }
}
