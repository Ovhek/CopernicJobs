/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Cole
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol_id", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Email(message = "{Email.alumno.correoContacto}")
    @Size(max = 128, message = "{Size.alumno.username}")
    @NotEmpty(message = "{NotEmpty.alumno.username}")
    @Column(name = "username")
    private String username;

    @Size(min = 8, max = 128, message = "{Size.alumno.password}")
    @NotEmpty(message = "{NotEmpty.alumno.password}")
    @Column(name = "password")
    private String password;

    @Column(name = "baja")
    private boolean baja;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_baja")
    private LocalDate fechaBaja;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private Rol rol;
}
