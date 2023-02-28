/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Albert
 */
@Data
@Entity
@Table(name="oferta")
public class Oferta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id", nullable = false, unique = true)
    private int id_empresa;
    @Column(name="fecha_validacion")
    private LocalDate fechaValidacion;
    @Column(name="enlace_pdf")
    private String enlacePDF;
    @Column(name="titulo")
    private String tituloOferta;
    @Column(name="descripcion")
    private String descripcionOferta;
    @Column(name="requisitos")
    private String requisitosAlumno;
    @Column(name="se_ofrece")
    private String seOfrece;
    @Column(name="fecha_peticion")
    private LocalDate fechaPeticion;
    @Column(name="baja")
    private boolean baja;
    @OneToOne
    private Empresa empresa;
}
