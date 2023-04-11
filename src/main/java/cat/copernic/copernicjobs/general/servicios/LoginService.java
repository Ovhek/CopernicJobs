/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.errores.UsuarioNoValidado;
import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.errores.UsuarioBajaException;
import cat.copernic.copernicjobs.model.Administrador;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio encargado de validar el login.
 *
 * @author Alex
 */
@Service("userDetailsService")
public class LoginService implements UserDetailsService {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private AdministradorService administradorService;

    private MessageSource messageSource;

    /**
     *
     * Busca y carga los detalles de un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a cargar.
     * @return Los detalles del usuario cargado como un objeto UserDetails.
     * @throws UsernameNotFoundException Si no se puede encontrar al usuario con
     * el nombre de usuario dado, si está de baja o no ha sido validado.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = (Alumno) alumnoService.buscarAlumnoPorUsername(username);

        if (usuario == null) {
            usuario = (Empresa) empresaService.buscarPorUsername(username);
        }

        if (usuario == null) {
            usuario = (Administrador) administradorService.buscarAdministradorPorUsername(username);
        }

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        if (usuario.getFechaBaja() != null) {
            throw new UsuarioBajaException("L'usuari està de baixa");
        }
        if (usuario.getFechaValidacion() == null) {
            throw new UsuarioNoValidado("L'usuari no està validat");
        }

        var roles = new ArrayList<GrantedAuthority>();

        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNom()));

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
