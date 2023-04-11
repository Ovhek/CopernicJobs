/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Controlador encargado de los archivos del sistema.
 *
 * @author Alex
 */
@Configuration
public class FileController implements WebMvcConfigurer {

    @Autowired
    private MessageSource messageSource;

    /**
     *
     * Método para agregar manejadores de recursos a la configuración de la
     * aplicación.
     *
     * @param registry objeto ResourceHandlerRegistry de Spring que permite
     * registrar manejadores de recursos
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + messageSource.getMessage("ruta.recursos", null, Locale.ENGLISH));
    }
}
