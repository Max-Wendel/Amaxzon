package br.com.amaxzon.security;

import br.com.amaxzon.model.Usuario;
import br.com.amaxzon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }


        return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());

    }
}
