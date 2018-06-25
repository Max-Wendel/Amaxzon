package br.com.amaxzon.controller;

import br.com.amaxzon.model.Usuario;
import br.com.amaxzon.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrarUsuario")
    public ModelAndView cadastrarUsuario(){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuario",new Usuario());
        return mv;
    }

    @RequestMapping("/salvarUsuario")
    public ModelAndView salvarUsuario(Usuario usuario){
        usuarioService.adicionarUsuario(usuario);
        return new ModelAndView("redirect:/listarUsuarios");
    }

    @RequestMapping("/excluirUsuario/{id}")
    public ModelAndView excluirUsuario(@PathVariable Long id){
        usuarioService.removerUsuario(id);
        return new ModelAndView("redirect:/listarUsuarios");
    }

    @GetMapping("/listarUsuarios")
    public ModelAndView listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        ModelAndView mv = new ModelAndView("gerenciar-usuarios");
        mv.addObject("todasOsUsuarios", usuarios );
        return mv;
    }

    @RequestMapping("{id}")
    public ModelAndView atualizarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuario",usuario);

        return mv;
    }

    @RequestMapping("/logar")
    public ModelAndView logar() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

}
