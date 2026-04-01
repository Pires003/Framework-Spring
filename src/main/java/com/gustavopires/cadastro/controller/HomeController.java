package com.gustavopires.cadastro.controller;

import com.gustavopires.cadastro.model.Usuario;
import com.gustavopires.cadastro.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    private final UsuarioRepository usuarioRepository;

    public HomeController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String abrirLogin() {
        return "index";
    }

    @GetMapping("/cadastro")
    public String abrirCadastro() {
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(Usuario usuario, Model model) {
        usuario.setAura(0);
        usuario.setNivel(0);

        usuarioRepository.save(usuario);

        carregarDadosTela(model, usuario);
        return "welcome";
    }

    @PostMapping("/login")
    public String fazerLogin(String email, String senha, Model model) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "index";
        }

        int novaAura = usuario.getAura() + 100;
        int novoNivel = novaAura / 300;

        if (novoNivel > 7) {
            novoNivel = 7;
        }

        usuario.setAura(novaAura);
        usuario.setNivel(novoNivel);

        usuarioRepository.save(usuario);

        carregarDadosTela(model, usuario);
        return "welcome";
    }

    private void carregarDadosTela(Model model, Usuario usuario) {
        model.addAttribute("nome", usuario.getNome());
        model.addAttribute("aura", usuario.getAura());
        model.addAttribute("nivel", usuario.getNivel());
        model.addAttribute("transformacao", getNomeTransformacao(usuario.getNivel()));
        model.addAttribute("background", getImagemPorNivel(usuario.getNivel()));
        model.addAttribute("progresso", calcularProgresso(usuario.getAura(), usuario.getNivel()));

        List<Usuario> ranking = usuarioRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Usuario::getAura).reversed())
                .toList();

        model.addAttribute("ranking", ranking);
    }

    private int calcularProgresso(int aura, int nivel) {
        if (nivel >= 7) {
            return 100;
        }

        int resto = aura % 300;
        return (resto * 100) / 300;
    }

    private String getNomeTransformacao(int nivel) {
        switch (nivel) {
            case 0:
                return "Goku Base";
            case 1:
                return "Super Saiyajin 1";
            case 2:
                return "Super Saiyajin 2";
            case 3:
                return "Super Saiyajin 3";
            case 4:
                return "Super Saiyajin God";
            case 5:
                return "Super Saiyajin God Azul";
            case 6:
                return "Instinto Superior Incompleto";
            case 7:
                return "Instinto Superior Completo";
            default:
                return "Instinto Superior Completo";
        }
    }

    private String getImagemPorNivel(int nivel) {
        switch (nivel) {
            case 0:
                return "base.jpg";
            case 1:
                return "ssj1.jpg";
            case 2:
                return "ssj2.jpg";
            case 3:
                return "ssj3.jpg";
            case 4:
                return "god.jpg";
            case 5:
                return "blue.jpg";
            case 6:
                return "ui_incompleto.jpg";
            case 7:
                return "ui_completo.jpg";
            default:
                return "ui_completo.jpg";
        }
    }
}