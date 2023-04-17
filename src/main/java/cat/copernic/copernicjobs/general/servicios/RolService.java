/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.dao.IncidenciaDAO;
import cat.copernic.copernicjobs.dao.RolDAO;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de la entidad Rol.
 * @author Alex
 */
@Service
public class RolService implements RolServiceInterface{

    @Autowired
    RolDAO rolDAO;

    /**
     * Lista todos los roles
     * @return Lista de roles.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Rol> listarRoles() {
        var a = rolDAO.findAll();
        a.forEach(rol -> Hibernate.initialize(rol.getModulos()));
        return a;
    }

    /**
     * Añade un rol a la db.
     * @param rol Rol a añadir.
     */
    @Transactional
    @Override
    public void anadirRol(Rol rol) {
        rolDAO.save(rol);
    }

    /**
     * Elimina un rol de la DB.
     * @param rol Rol a eliminar.
     */
    @Transactional
    @Override
    public void eliminarRol(Rol rol) {
       rolDAO.delete(rol);
    }

    /**
     * Busca un rol en la DB.
     * @param rol Rol a buscar.
     * @return Devuelve el rol si existe, si no nulo.
     */
    @Transactional(readOnly = true)
    @Override
    public Rol buscarRol(Rol rol) {
        Rol rol_ = rolDAO.findById(rol.getId()).orElse(null);
        Hibernate.initialize(rol_.getModulos());
        return rol_;
    }
    

    
}
