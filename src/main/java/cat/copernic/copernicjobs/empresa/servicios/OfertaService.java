/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa.servicios;

import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.Collectors;
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

    @Override
    public List<Oferta> llistarOfertasUltimaSemana() {
        
        //Listado de todas las ofertas
        List<Oferta> ofertas = (List<Oferta>) ofertaDao.findAll();
        
        //Devuelve la semana de la fecha actual.
        TemporalField semanaDelAño = WeekFields.of(java.util.Locale.getDefault()).weekOfWeekBasedYear();
        
        //devuelve la fecha actual
        LocalDate fechaActual = LocalDate.now();
        
        //numero de la semana de la fecha actual
        int semanaDeLaFechaActual = fechaActual.get(semanaDelAño);
        int annoActual = fechaActual.getYear();

        //Filtrar la lista de ofertas a solo aquellas de la semana actual.
        List<Oferta> ofertasFiltradas = ofertas.stream().filter(oferta -> {
           int semanaOferta = oferta.getFechaValidacion().get(semanaDelAño);
           int annoOferta = oferta.getFechaValidacion().getYear();
           return semanaDeLaFechaActual == semanaOferta && annoOferta == annoActual;
        }).collect(Collectors.toList());
        
        return ofertasFiltradas;
    }
    
}
