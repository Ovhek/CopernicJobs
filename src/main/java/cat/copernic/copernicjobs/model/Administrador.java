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
import lombok.Data;

/**
 *
 * @author joang
 */
@Data
@Entity
@DiscriminatorValue("2")
public class Administrador extends Usuario {
    @Size(max = 50)
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    
    @Size(max = 200)
    @Column(name = "apellidos")
    @NotEmpty
    private String apellido;
}
