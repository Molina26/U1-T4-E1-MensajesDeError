package utez.edu.mx.manejoErrores.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utez.edu.mx.manejoErrores.entity.Role;
import utez.edu.mx.manejoErrores.entity.UserApp;
import utez.edu.mx.manejoErrores.repository.IUserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private IUserRepository iUserRepository;

  private Logger logger = LoggerFactory.getLogger(IUserRepository.class);

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserApp usuario = iUserRepository.findUserByUsername(username);

    if (usuario == null) {
      logger.error("El usuario ".concat(username).concat( " no tiene acceso a este proyecto"));
      throw new UsernameNotFoundException("No se ha encontrado información sobre el usuario ".concat(username));
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    
    for (Role role : usuario.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
    }


    if (authorities.isEmpty()) {
      logger.error("El usuario ".concat(username).concat( " no tiene un rol asignado"));
      throw new UsernameNotFoundException("El usuario " + username + " no tiene un rol asignado");
    }

    return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
  }

}
