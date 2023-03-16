/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase encargada de encriptar contraseñas.
 * @author Alex
 */
public abstract class EncriptarContrasenya {

    /**
     * Encripta la contraseña pasada por parametro
     * @param password Contraseña a encriptar
     * @return contraseña encriptada
     */
    public static String encryptar(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
