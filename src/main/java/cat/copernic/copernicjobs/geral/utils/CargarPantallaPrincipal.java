/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.geral.utils;

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
     * @param nombreBotones lista de los nombres de los botones
     * @param ruta Ruta de la página a cargar sobre la plantilla
     * @param archivo Archivo html (sin .html) de la página la cual cargar en la plantilla.
     * @return devuelve la plantilla
     */
    public static String cargar(Model model, ArrayList<String> nombreBotones, String ruta, String archivo){
        model.addAttribute("nombreBotones",nombreBotones);
        model.addAttribute("ruta",ruta);
        model.addAttribute("archivo",archivo);
        return "_plantillaPaginaPrincipal";
    }
}
