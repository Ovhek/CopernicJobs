/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
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

    @Size(min = 14, message = "{Size.alumno.tarjetaSanitaria}")
    @Column(name = "tarjeta_sanitaria")
    private String tarjetaSanitaria;

    @Size(min = 8, message = "{Size.alumno.segSocial}")
    @Column(name = "seg_social")
    private String segSocial;

    @Pattern(regexp =  "^[0-9XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]$", message = "{Pattern.alumno.dni}")
    @Size(max = 9, min = 9, message = "{Size.alumno.dni}")
    @Column(name = "dni")
    private String dni;

    @URL(message = "{URL.alumno.linkedin}")
    @Column(name = "linkedin")
    private String linkedin;

    @URL(message = "{URL.alumno.portafoliPersonal}")
    @Column(name = "portafoli_personal")
    private String portafoliPersonal;

    @URL(message = "{URL.alumno.curriculumOnline}")
    @Column(name = "curriculum_online")
    private String curriculumOnline;

    @URL(message = "{URL.alumno.pdfLink}")
    @Column(name = "pdf_link")
    private String pdfLink;

    @URL(message = "{URL.alumno.avatarLink}")
    @Column(name = "avatar_link")
    private String avatarLink;
}
