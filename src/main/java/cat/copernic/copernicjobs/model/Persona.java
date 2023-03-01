/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Cole
 */

@Data
@Entity
public class Persona extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cod_postal")
    private String codPostal;
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "movil")
    private String movil;
    
    @Column(name = "sexo")
    private int sexo;
    
    @Column(name = "sexo_desc")
    private String sexoDesc;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "correo_contacto")
    private String correoContacto;
    
}
