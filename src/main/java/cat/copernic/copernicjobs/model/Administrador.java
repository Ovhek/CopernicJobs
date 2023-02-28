/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 *
 * @author joang
 */
@Data
@Entity
@DiscriminatorValue("2")
public class Administrador extends Usuario {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
}
