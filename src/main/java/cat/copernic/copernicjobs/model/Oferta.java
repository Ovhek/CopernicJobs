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
    @Column(name="id_empresa")
    private int id_empresa;
    @Column(name="id_empresa")
    private LocalDate fechaValidacion;
    @Column(name="id_empresa")
    private String enlacePDF;
    @Column(name="id_empresa")
    private String tituloOferta;
    @Column(name="id_empresa")
    private String descripcionOferta;
    @Column(name="id_empresa")
    private String requisitosAlumno;
    @Column(name="id_empresa")
    private String seOfrece;
    @Column(name="id_empresa")
    private LocalDate fechaPeticion;
    @Column(name="id_empresa")
    private boolean baja;
    @OneToOne
    private Empresa empresa;
}
