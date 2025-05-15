package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.services.CookieService;
import com.web.meutatame.tcc.services.RelatorioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nome"));

        int totalAlunos = relatorioService.contarAlunos();
        double lucro = relatorioService.calcularLucroMensal();

        model.addAttribute("totalAlunos", totalAlunos);
        model.addAttribute("lucro", lucro);

        return "home/index";
    }
}

