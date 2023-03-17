/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Albert
 */
@Data
@Entity
@Table(name="empresa")
@DiscriminatorValue("3")
public class Empresa extends Persona {
    
    @Column(name="nombre_empresa")
    @Size(max=50, message = "{Size.empresa.nombreEmpresa}")
    private String nombreEmpresa;
    
    @Column(name="descripcion_empresa")
    private String descripcionEmpresa;
    
    @Column(name="webEmpresa")
    @Size(max=50)
    @URL(message ="{URL.empresa.webEmpresa}")
    private String webEmpresa;
    
    @Column(name="movil_empresa")
    @Size(max=12, message="{Size.empresa.movilEmpresa}")
    private String movilEmpresa;
    
    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oferta> ofertas;
}
