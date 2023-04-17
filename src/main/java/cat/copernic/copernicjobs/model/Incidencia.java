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
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;


/**
 * Clase entidad Incidencia.
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
    @NotEmpty(message = "{NotEmpty.incidencia.desc}")
    private String descripcion;
    @Column(name = "fechaIncidencia")
    private LocalDate fechaIncidencia;
    @Column(name = "estado")
    private int estado;
    
    @Column (name="titulo")
    @NotEmpty(message = "{NotEmpty.incidencia.titulo}")
    private String titulo;
    @OneToOne()
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
}
