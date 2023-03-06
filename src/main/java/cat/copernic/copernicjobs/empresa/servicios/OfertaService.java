/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa.servicios;

import cat.copernic.copernicjobs.DAO.EmpresaDAO;
import cat.copernic.copernicjobs.DAO.OfertaDAO;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Albert
 */
@Service
public class OfertaService implements OfertaServiceInterface {
    
    
    @Autowired
    private OfertaDAO ofertaDao;
    
    
    @Override
    public List<Oferta> llistarOfertas() {
        return (List<Oferta>) ofertaDao.findAll();
    }

    @Override
    public void afegirOferta(Oferta oferta) {
        this.ofertaDao.save(oferta); 
    }

    @Override
    public void eliminarOferta(Oferta oferta) {
        this.ofertaDao.delete(oferta);
    }

    @Override
    public Oferta cercarOferta(Oferta oferta) {
        return this.ofertaDao.findById(oferta.getId()).orElse(null);
    }
    
}
