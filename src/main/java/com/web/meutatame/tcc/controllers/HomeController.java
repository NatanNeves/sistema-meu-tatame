package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.services.CookieService;
import com.web.meutatame.tcc.services.DashService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {

    @Autowired
    private DashService dashService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nome"));

        long totalAlunos = dashService.contarTotalAlunos();
        model.addAttribute("totalAlunos", totalAlunos);

        return "home/index";
    }
}

