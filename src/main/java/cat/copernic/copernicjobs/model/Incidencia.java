/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;


/**
 *
 * @author joang
 */
@Data
@Entity
public class Incidencia implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id //Indica al sistema que l'atribut id és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_incidencia")
    private LocalDate fecha_incidencia;
    @Column(name = "estado")
    private int estado;
    
    @OneToOne()
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
}
