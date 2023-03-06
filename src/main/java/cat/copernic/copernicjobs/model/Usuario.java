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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@DiscriminatorColumn(name="rol_id", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasenya")
    private String contrasenya;
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
    
    @OneToOne()
    @PrimaryKeyJoinColumn(name="rol_id")
    private Rol rol;
}
