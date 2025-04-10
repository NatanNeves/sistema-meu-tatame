package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlunosController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/alunos")
    public String index(Model model){
        List<Aluno> alunos = alunoRepository.findAll();
        System.out.println("Alunos encontrados: " + alunos.size());
        model.addAttribute("alunos", alunos);
        return "alunos/index";
    }

}
