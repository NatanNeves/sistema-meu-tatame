package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AlunosController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/alunos")
    public String index(Model model){
        List<Aluno> alunos = alunoRepository.findAll();
        model.addAttribute("alunos", alunos);
        return "alunos/index";
    }

    @GetMapping("/alunos/novo")
    public String novo(){
        return "alunos/novo";
    }

    @PostMapping("/alunos/criar")
    public String criar(Aluno aluno){
         alunoRepository.save(aluno);
         return "redirect:/alunos";
    }

    @GetMapping("/alunos/{id}/excluir")
    public String excluir(@PathVariable int id) {
        alunoRepository.deleteById(id); // Deletar um aluno pelo ID
        return "redirect:/alunos"; // Redireciona para a lista de alunos
    }
}
