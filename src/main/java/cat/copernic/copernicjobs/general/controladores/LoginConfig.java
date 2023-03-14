/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.general.servicios.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Alex
 */
@Configuration //Indica al sistema que és una classe de configuració
@EnableWebSecurity //Habilita la seguretat web
public class LoginConfig {

    @Autowired
    private LoginService loginService; //Objecte per recuperar l'usuari

    /* AUTENTICACIÓ */
 /* ALEX */
    /**
     * Injectem mitjançant @Autowired, els mètodes de la classe
     * AuthenticationManagerBuilder. Mitjançant aquesta classe cridarem al
     * mètode userDetailsService de la classe AuthenticationManagerBuilder què
     * és el mètode que realitzarà l'autenticació. Per parm̀etre el sistema li
     * passa l'usuari introduit en el formulari d'autenticació. Aquest usuari
     * ens el retorna el mètode loadUserByUsername implementat en UsuariService.
     *
     * Cridem al mètode passwordEncoder passant-li com a paràmetre un objecte de
     * tipus BCryptPasswordEncoder() per encriptar el password introduït per
     * l'usuari en el moment d'autenticar-se i comparar-lo amb l'encriptació del
     * password guardat a la BBDD corresponent a l'username introduït també en
     * l'autenticació.
     */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean //L'indica al sistema que el mètode és un Bean, en aquest cas perquè crea un objecte de la classe HttpSecurity
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/inici").authenticated()
                .requestMatchers("/alumne/**","/alumne/inici").hasRole("alumne")
                .requestMatchers("/empresa/**").hasRole("empresa")
                .requestMatchers("/administrador/**").hasRole("administrador")
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated() //Qualsevol altre sol.licitud que no coincideixi amb les regles anteriors cal autenticació
        )
                .formLogin((form) -> form //Objecte que representa el formulari de login personalitzat que utilitzarem
                .loginPage("/login") //Pàgina on es troba el formulari per fer login personalitzat
                .permitAll()
                .defaultSuccessUrl("/inici")//Permet acceddir a tothom
                )
                .exceptionHandling((exception) -> exception //Quan es produeix una excepcció 403, accés denegat, mostrem el nostre missatge
                .accessDeniedPage("/errors/error403"))
                .build();

    }
}
