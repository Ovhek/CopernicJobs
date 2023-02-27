/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Cole
 */
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol_id", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario {
    //protected porque es superclase
    
    @Column(name = "correo")
    protected String correo;
    @Column(name = "contrasenya")
    protected String contrasenya;
    @Column(name = "baja")
    protected boolean baja;
    @Column(name = "fecha_registro")
    protected LocalDate fechaRegistro;
    @Column(name = "fecha_baja")
    protected LocalDate fechaBaja;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected int ID;
    
    @OneToOne
    @JoinColumn(name="rol_id")
    protected Rol rol;
}
