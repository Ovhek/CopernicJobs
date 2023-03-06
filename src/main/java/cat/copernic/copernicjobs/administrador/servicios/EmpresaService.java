/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.DAO.EmpresaDAO;
import cat.copernic.copernicjobs.model.Empresa;
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
public class EmpresaService implements EmpresaServiceInterface {

    /*Atribut que defineix una empresaDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
     */
    @Autowired
    private EmpresaDAO empresa;

    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant la notació @Transactional l'indiquem al sistema que el mètode és una transacció.
     *Això permet que no hi hagi problemes si estem fent més d'una transacció al mateix temps.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Empresa> llistarEmpreses() {
        /*Cridem al mètode findAll() de CrudRepository perquè ens retorni el llistat de noticies de la BBDD.
         *findAll() retorna un objecte, per tant hem de fer un cast perquè l'objecte sigui un List de noticies
         */
        return (List<Empresa>) empresa.findAll();
    }

    @Override
    @Transactional
    public void afegirEmpresa(Empresa empresa) {
        /*Cridem al mètode save() de CrudRepository perquè afegeixi la empresa passada com a paràmetre,
         *a la taula noticia de la BBDD copernicjobs.
         */
        this.empresa.save(empresa);
    }

    @Override
    @Transactional
    public void eliminarEmpresa(Empresa empresa) {

        /*Cridem al mètode delete() de CrudRepository perquè elimini la noticia passada com a paràmetre,
         *de la taula noticia de la BBDD copernicjobs.
         */
        this.empresa.delete(empresa);
    }

    @Override
    @Transactional(readOnly = true) //Igual que en el mètode llistarNoticies, no modifiquem la informació de la BBDD
    public Empresa cercarEmpresa(Empresa empresa) {
        /*Cridem al mètode findById() de CrudRepository perquè ens retorni la noticia passada com a paràmetre.
         *El paràmetre que li passem a aquest mètode, ha de ser la clau primària de l'entitat, en el nostre 
         *cas la noticia.
         *
         *Si la noticia no existeix retornarà null (orElse(null)).
         */

        return this.empresa.findById(empresa.getId()).orElse(null);
    }

}
