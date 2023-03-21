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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Albert
 */
@Data
@Entity
@Table(name = "empresa")
@DiscriminatorValue("3")
public class Empresa extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nombre_empresa")
    @NotEmpty(message = "{NotEmpty.empresa.nombreEmpresa}")
    @Size(max = 50, message = "{Size.empresa.nombreEmpresa}")
    private String nombreEmpresa;

    @Size(min = 30, message = "{Size.empresa.descripcionEmpresa}")
    @Column(name = "descripcion_empresa")
    private String descripcionEmpresa;

    @URL(message = "{URL.empresa.webEmpresa}")
    @Column(name = "webEmpresa")
    private String webEmpresa;

    @Pattern(regexp = "\\d{9}", message = "{Pattern.empresa.movilEmpresa}")
    @Column(name = "movil_empresa")
    private String movilEmpresa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oferta> ofertas;
}
