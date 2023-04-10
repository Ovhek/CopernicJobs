/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.general.servicios.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuración de la Auntentificación
 *
 * @author Alex
 */
@Configuration //Indica al sistema que és una classe de configuració
@EnableWebSecurity //Habilita la seguretat web
public class LoginConfig {

    @Autowired
    private LoginService loginService; //Objecte per recuperar l'usuari

    /**
     *
     * Método para configurar la autenticación en la aplicación.
     *
     * @param auth objeto AuthenticationManagerBuilder de Spring Security que
     * permite configurar la autenticación
     * @throws Exception si ocurre un error al configurar la autenticación
     */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     *
     * Método para crear un bean de MessageSource, que se utiliza para localizar
     * mensajes de texto en la aplicación.
     *
     * @return un objeto MessageSource configurado para buscar mensajes en el
     * archivo "messages.properties"
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     *
     * Método para crear un bean de LocalValidatorFactoryBean, que se utiliza
     * para validar objetos en la aplicación.
     *
     * @param messageSource objeto MessageSource que se utiliza para localizar
     * mensajes de validación
     * @return un objeto LocalValidatorFactoryBean configurado para utilizar el
     * objeto MessageSource especificado
     */
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    /**
     *
     * Método para crear un bean de AuthenticationFailureHandler, que se utiliza
     * para manejar la autenticación fallida.
     *
     * @return un objeto CustomAuthenticationFailureHandler para manejar la
     * autenticación fallida.
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    /**
     *
     * Método que crea un bean de tipo SecurityFilterChain, que se utiliza para
     * configurar la seguridad de la aplicación web.
     *
     * @param http el objeto HttpSecurity utilizado para configurar la seguridad
     * de la aplicación web.
     * @return un objeto SecurityFilterChain para la configuración de la
     * seguridad.
     * @throws Exception si ocurre algún error durante la configuración de la
     * seguridad.
     */
    @Bean //L'indica al sistema que el mètode és un Bean, en aquest cas perquè crea un objecte de la classe HttpSecurity
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeHttpRequests((requests) -> requests
                .requestMatchers("/inici").authenticated()
                .requestMatchers("/files/**").authenticated()
                .requestMatchers("/crearIncidencia").authenticated()
                .requestMatchers("/alumne/**").authenticated()
                .requestMatchers("/empresa/**").authenticated()
                .requestMatchers("/administrador/**").authenticated()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated() //Qualsevol altre sol.licitud que no coincideixi amb les regles anteriors cal autenticació
        )
                .formLogin((form) -> form //Objecte que representa el formulari de login personalitzat que utilitzarem
                .loginPage("/login") //Pàgina on es troba el formulari per fer login personalitzat
                .failureHandler(authenticationFailureHandler())
                .permitAll()
                .defaultSuccessUrl("/inici")//Permet acceddir a tothom
                )
                .httpBasic()
                .and()
                .logout()
                .and()
                .exceptionHandling((exception) -> exception //Quan es produeix una excepcció 403, accés denegat, mostrem el nostre missatge
                .accessDeniedPage("/errors/error403"))
                .build();

    }
}
