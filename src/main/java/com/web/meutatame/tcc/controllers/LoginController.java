package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Administrador;
import com.web.meutatame.tcc.repository.AdministradorRepository;
import com.web.meutatame.tcc.services.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private AdministradorRepository admRepository;

    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) throws IOException {
        Administrador adm = this.admRepository.Login(admParam.getEmail(), admParam.getSenha());

        if (adm != null) {
            int tempoLogado = 60 * 60; // 1 hora
            if (lembrar != null) {
                tempoLogado = 60 * 60 * 24 * 365; // 1 ano
            }

            CookieService.setCookie(response, "usuarioId", String.valueOf(adm.getId()), tempoLogado);
            CookieService.setCookie(response, "nomeUsuario", adm.getNome(), tempoLogado);

            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login/index";
    }

    @GetMapping("/sair")
    public String sair(HttpServletResponse response) throws IOException {
        CookieService.setCookie(response, "usuarioId", "", 0);
        CookieService.setCookie(response, "nomeUsuario", "", 0);
        return "redirect:/login";
    }
}

