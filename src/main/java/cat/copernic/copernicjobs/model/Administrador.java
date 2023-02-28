/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author joang
 */
@Data
@Entity
public class Administrador extends Usuario {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
}
