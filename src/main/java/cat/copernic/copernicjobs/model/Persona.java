/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(max = 50)
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;

    @Pattern(regexp = "/^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$/")
    @Size(min = 5, max = 5)
    @Column(name = "cod_postal")
    private String codPostal;

    @Size(max = 50)
    @Column(name = "municipio")
    private String municipio;

    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;

    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "movil")
    private String movil;

    @Column(name = "sexo")
    private int sexo;

    @Column(name = "sexo_desc")
    private String sexoDesc;

    @Size(max = 200)
    @Column(name = "apellidos")
    @NotEmpty
    private String apellidos;

    @Email
    @Size(max = 50)
    @Column(name = "correo_contacto")
    private String correoContacto;

}
