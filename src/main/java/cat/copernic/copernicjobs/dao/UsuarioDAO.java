/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Usuario;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repositorio que se utiliza como un repositorio generico para todas las clases que hereden de la entidad Usuario.
 * Dado que esta entidad es un mappedSuperClass no se pueden usar directamente en el DAO pues es abstracta.
 * 
 * Usando esta clase proporcionamos las operaciones CRUD a todos los dao que sus entidades sean hijas de Usuario.
 * 
 * @param <Entidad> La Entidad sobre la cual aplicar el DAO.  {@link cat.copernic.copernicjobs.model.Alumno Alumno}, {@link cat.copernic.copernicjobs.model.Empresa Empresa} o {@link cat.copernic.copernicjobs.model.Administrador Administrador}.
 * @param <ID> Tipo de Objecto que utiliza la clase como identificador. (Normalmente {@link Integer})
 * 
 * @author Alex
 */

@NoRepositoryBean
public interface UsuarioDAO<Entidad extends Usuario, ID extends Serializable> extends JpaRepository<Entidad, ID>{
    
    Entidad findByUsername(String username);
    
}
