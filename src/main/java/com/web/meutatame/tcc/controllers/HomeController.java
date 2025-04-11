package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.services.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(Model model, HttpServletRequest request)throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nome"));
        return "home/index";
    }
}
