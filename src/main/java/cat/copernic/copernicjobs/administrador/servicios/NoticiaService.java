/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.NoticiaDAO;
import cat.copernic.copernicjobs.model.Noticia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joang
 */
@Service
public class NoticiaService implements NoticiaServiceInterface{

    /*Atribut que defineix una noticiaDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
    */
    @Autowired
    private NoticiaDAO noticia;
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant la notació @Transactional l'indiquem al sistema que el mètode és una transacció.
     *Això permet que no hi hagi problemes si estem fent més d'una transacció al mateix temps.
    */

    /*LListar noticies de la taula noticia de la BBDD copernicjobs*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
    */    
    @Transactional(readOnly=true) 
    public List<Noticia> llistarNoticies() {
        
        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de noticies de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de noticies
        */
        return (List<Noticia>) noticia.findAll(); 
    }
    
    /*Afegir la noticia passada per paràmetre a la taula noticia de la BBDD copernicjobs*/
    @Override
    /*En aquest cas hi haurà COMMITS i ROLLBACKS, ja que modifiquem la informació de la BBDD, per tant,
     *utilitzarem aquesta notació sense passar-li cap paràmetre perquè es puguin fer els COMMITS 
     *i ROLLBACKS.
    */ 
    @Transactional
    public void afegirNoticia(Noticia noticia) {
        
        /*Cridem al mètode save() de CrudRepository perquè afegeixi la noticia passada com a paràmetre,
         *a la taula noticia de la BBDD copernicjobs.
        */
        this.noticia.save(noticia); 
    }
    
    /*Eliminar la noticia passada per paràmetre de la taula noticia de la BBDD copernicjobs*/
    @Override
    @Transactional //Igual que en el mètode afegirGos, modifiquem la informació de la BBDD
    public void eliminarNoticia(Noticia noticia) {
        
        /*Cridem al mètode delete() de CrudRepository perquè elimini la noticia passada com a paràmetre,
         *de la taula noticia de la BBDD copernicjobs.
        */
        this.noticia.delete(noticia);
        
    }
    
    /*Cercar la noticia passada per paràmetre en la taula noticia de la BBDD copernicjobs*/
    @Override
    @Transactional(readOnly=true) //Igual que en el mètode llistarNoticies, no modifiquem la informació de la BBDD
    public Noticia cercarNoticia(Noticia noticia) {
        
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la noticia passada com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la noticia.
         *
         *Si la noticia no existeix retornarà null (orElse(null)).
        */ 

        return this.noticia.findById(noticia.getId()).orElse(null);
        
    }
}
