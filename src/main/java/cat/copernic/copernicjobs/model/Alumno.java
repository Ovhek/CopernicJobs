/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Cole
 */
@Data
@Entity
@DiscriminatorValue("1")
public class Alumno extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 14)
    @Column(name = "tarjeta_sanitaria")
    private String tarjetaSanitaria;

    @Size(min = 8)
    @Column(name = "seg_social")
    private String segSocial;

    @Size(max = 9, min = 9)
    @Column(name = "dni")
    private String dni;

    @URL
    @Column(name = "linkedin")
    private String linkedin;

    @URL
    @Size(max = 50)
    @Column(name = "portafoli_personal")
    private String portafoliPersonal;

    @URL
    @Size(max = 50)
    @Column(name = "curriculum_online")
    private String curriculumOnline;

    @URL
    @Size(max = 50)
    @Column(name = "pdf_link")
    private String pdfLink;

    @URL
    @Size(max = 50)
    @Column(name = "avatar_link")
    private String avatarLink;
}
