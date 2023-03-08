/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.DAO.OfertaDAO;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joang
 */
@Service
public class OfertaService implements OfertaServiceInterface{

    @Autowired
    private OfertaDAO oferta;
    
    @Override
    @Transactional(readOnly=true)
    public List<Oferta> llistarOfertes(int id) {
        return(List<Oferta>) oferta.findAllByEmpresaId(id);
    }

    @Override
    @Transactional
    public void afegirOferta(Oferta oferta) {
        /*Cridem al mètode save() de CrudRepository perquè afegeixi la noticia passada com a paràmetre,
         *a la taula oferta de la BBDD copernicjobs.
        */
        this.oferta.save(oferta); 
    }

    @Override
    @Transactional
    public void eliminarOferta(Oferta oferta) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini la noticia passada com a paràmetre,
         *de la taula oferta de la BBDD copernicjobs.
        */
        this.oferta.delete(oferta);
    }

    @Override
    @Transactional(readOnly=true)
    public Oferta cercarOferta(Oferta oferta) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la noticia passada com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la noticia.
         *
         *Si la noticia no existeix retornarà null (orElse(null)).
        */ 

        return this.oferta.findById(oferta.getId()).orElse(null);
    }
    
}
