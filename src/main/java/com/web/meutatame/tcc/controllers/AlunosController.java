package com.web.meutatame.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunosController {
    @GetMapping("/alunos")
    public String index(){
        return "alunos/index";
    }
}
