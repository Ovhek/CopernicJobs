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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Albert
 */
@Data
@Entity
@Table(name="oferta")
public class Oferta {
    
    @Column(name="fecha_validacion")
    private LocalDate fechaValidacion;
    
    @Column(name="enlace_pdf")
    @URL(message="{URL.oferta.enlacePDF}")
    private String enlacePDF;
    
    @Column(name="titulo")
    @Size(max=50, message="{Size.oferta.tituloOferta}")
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
    
    @OneToOne()
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private int id;
    
    @OneToMany(mappedBy="oferta")
    private List<Inscripcion> inscripciones;
        
}
