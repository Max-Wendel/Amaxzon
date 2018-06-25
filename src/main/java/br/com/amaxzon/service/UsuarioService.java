package br.com.amaxzon.service;

import br.com.amaxzon.model.Usuario;
import br.com.amaxzon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public void adicionarUsuario(Usuario usuario){
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId( Long id){
        return usuarioRepository.getOne(id);
    }

    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
