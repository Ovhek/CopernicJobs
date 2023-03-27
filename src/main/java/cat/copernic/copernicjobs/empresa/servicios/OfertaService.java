/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa.servicios;

import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    @Override
    public List<Oferta> llistarOfertasUltimaSemana() {
        
        LocalDate inicioSemana = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate finSemana = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        
        
        return ofertaDao.findByFechaValidacionBetween(inicioSemana, finSemana);
    }

    @Override
    public List<Oferta> ordenarOfertasAlfabetico() {
       return null; //(List<Oferta>) this.ofertaDao.findAll().sort(Oferta::getTituloOferta);
    }

    @Override
    public List<Oferta> filtrarOfertasOrdenacion(String busqueda, String ordenacion) {

        if(ordenacion.equals("alfabetico")){
            return (List<Oferta>) ofertaDao.findAll();
            //return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByTituloOferta(busqueda, ordenacion);
        }
        else if(ordenacion.equals("dataPublicacio")){
            return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaValidacionAsc(busqueda);
        }
        else if(ordenacion.equals("numeroCandidatos")){
            return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByNumeroInscripcionesAsc(busqueda);
        }
        else if(ordenacion.equals("ofertasActivas")){
            return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaPeticionAsc(busqueda);
        }
        else if(ordenacion.equals("ofertasPublicadas")){
            return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaValidacionAsc(busqueda);
        }
        return null;
    }

    @Override
    public List<Oferta> listarPorNombre(String nombre) {
        return (List<Oferta>) ofertaDao.findByNombreEmpresa(nombre);
    }
    
}
