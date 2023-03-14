/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.model.Administrador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joang
 */
@Service
public class AdministradorService implements AdministradorServiceInterface{
    @Autowired
    AdministradorDAO administradorDAO;

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

    @Override
    public Administrador buscarAdministrador(Administrador alumno) {
        return administradorDAO.findById(alumno.getId()).orElse(null);
    }
}
