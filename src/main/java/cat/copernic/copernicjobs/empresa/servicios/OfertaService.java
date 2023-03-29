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
import java.util.SortedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<Oferta> filtrarOfertasOrdenacion(String busqueda, String ordenacion, String username) {
        switch (ordenacion) {
            case "alfabetico":
                if (busqueda == null) {
                    return (List<Oferta>) ofertaDao.findByNombreEmpresaOrderByTituloOfertaAsc(username);
                } else {
                    return (List<Oferta>) ofertaDao.findByTituloOfertaOrderByTituloOfertaAsc(busqueda, username);
                }
            case "dataPublicacio":
                if (busqueda == null) {
                    //Estas dos deberian funcionar, no estan testeadas.
                    return (List<Oferta>) this.ofertaDao.findByNombreEmpresaOrderByFechaValidacionAsc(username);
                } else {
                    return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaValidacionAsc(busqueda, username);
                }
            case "numeroCandidatos":
                if (busqueda == null) {
                    return (List<Oferta>) this.ofertaDao.findByNombreEmpresaOrderByNumeroInscripcionesAsc(username);
                } else {
                    return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByNumeroInscripcionesAsc(busqueda, username);
                }

            case "ofertasActivas":
                if (busqueda == null) {
                    return (List<Oferta>) this.ofertaDao.findByNombreEmpresaOrderByFechaPeticionAsc(username);
                } else {
                    return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaPeticionAsc(busqueda, username);
                }
            case "ofertasPublicadas":
                if (busqueda == null) {
                    return (List<Oferta>) this.ofertaDao.findByNombreEmpresaOrderByFechaValidacionAsc(username);
                } else {
                    return (List<Oferta>) this.ofertaDao.findByTituloOfertaOrderByFechaValidacionAsc(busqueda, username);
                }
            case "0":
                if (busqueda == null) {
                    return (List<Oferta>) ofertaDao.findByNombreEmpresaOrderByTituloOfertaAsc(username);
                } else {
                    return (List<Oferta>) ofertaDao.findByTituloOfertaOrderByTituloOfertaAsc(busqueda, username);
                }

        }
        return null;
    }

    public List<Oferta> filtrarOfertasOrdenacionAlumno(String busqueda, String anything) {
        Sort sort = Sort.by("tituloOferta");
        switch (anything.toLowerCase()) {
            case "alfabetico":
                sort = Sort.by("tituloOferta");
                break;
            case "datapublicacio":
                sort = Sort.by(Sort.Direction.DESC,"fechaValidacion");
                break;
            default:
                sort = Sort.by("tituloOferta");
                break;
        }
        return ofertaDao.findByTituloDescripcionOfertaOrderByAnythingAscOrDesc(busqueda, sort);
    }

    @Override
    public List<Oferta> listarPorNombre(String nombre) {
        return (List<Oferta>) ofertaDao.findByNombreEmpresa(nombre);
    }

}
