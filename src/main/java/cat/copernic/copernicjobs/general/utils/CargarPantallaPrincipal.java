/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.utils;

import java.util.ArrayList;
import org.springframework.ui.Model;

/**
 *
 * Función especializada en cargar la pantalla principal como plantilla para las
 * demás pantallas.
 */
public abstract class CargarPantallaPrincipal {

    /**
     * Función para cargar la plantilla de la pantalla Principal.
     * @param model Modelo de springboot UI
     * @param tipo Tipo de Menu
     * @param ruta Ruta de la página a cargar sobre la plantilla
     * @param archivo Archivo html (sin .html) de la página la cual cargar en la plantilla.
     * @return devuelve la plantilla
     */
    public static String cargar(Model model, NavBarType tipo, String ruta, String archivo){
        
        //Ruta del archvio de navegación
        String rutaNav = "";
        //Nombre del archvio de navegación sin .html
        String archivoNav = "";
        switch (tipo) {
            case ALUMNO:
                rutaNav = "alumno/";
                archivoNav = "_navAlumno";
                break;
            case EMPRESA:
                rutaNav = "empresa/";
                archivoNav = "_navEmpresa";
                break;
            case ADMINISTRADOR:
                rutaNav = "administrador/";
                archivoNav = "_navAdministrador";
                break;
            default:
                throw new AssertionError();
        }
        
        //Añadimos los atributos rutaNav y archivoNav dependiendo del tipo de navegación
        model.addAttribute("rutaNav",rutaNav);
        model.addAttribute("archivoNav",archivoNav);
        
        //Añadimos los atributos rutaContenido y archivoContenido al HTML dependiendo del valor
        model.addAttribute("rutaContenido",ruta);
        model.addAttribute("archivoContenido",archivo);
        
        //Devuelve la plantilla de la página Principal con la navegación añadida y el contenido dinamico añadido.
        return "_plantillaPaginaPrincipal";
    }
}
