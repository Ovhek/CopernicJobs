/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.model.Administrador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joang
 */
@Service
public class AdministradorService implements AdministradorServiceInterface {

    public void setAdministradorDAO(AdministradorDAO administradorDAO) {
        this.administradorDAO = administradorDAO;
    }
    
    @Autowired
    AdministradorDAO administradorDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Administrador> listarAdministradors() {
        return (List<Administrador>) administradorDAO.findAll();
    }

    @Override
    public void anadirAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
    }

    @Override
    public void eliminarAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
    }

    @Transactional(readOnly = true)
    @Override
    public Administrador buscarAdministrador(Administrador administrador) {
        return administradorDAO.findById(administrador.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Administrador buscarAdministradorPorUsername(String username) {
        return administradorDAO.findByUsername(username);
    }
}
