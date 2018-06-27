package br.com.amaxzon.controller;

import br.com.amaxzon.model.Usuario;
import br.com.amaxzon.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(Usuario usuario){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuario",new Usuario());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarUsuario(Usuario usuario){
        usuarioService.adicionarUsuario(usuario);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/logar")
    public ModelAndView logar() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

}
