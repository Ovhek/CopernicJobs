/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/**
 * Clase entidad Administrador.
 * @author joang
 */
@Data
@Entity
@DiscriminatorValue("2")
public class Administrador extends Usuario implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "{NotEmpty.administrador.nombre}")
    @Size(max = 50, message = "{Size.administrador.nombre}")
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty(message = "{NotEmpty.administrador.apellido}")
    @Size(max = 200, message = "{Size.administrador.apellido}")
    @Column(name = "apellidos")
    private String apellidos;
}
