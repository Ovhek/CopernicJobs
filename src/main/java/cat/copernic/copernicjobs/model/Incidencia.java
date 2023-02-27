/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author joang
 */
@Data
@Entity
public class Incidencia{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaIncidencia")
    private LocalDate fechaIncidencia;
    @Column(name = "estado")
    private int estado;
    
    @OneToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
}
