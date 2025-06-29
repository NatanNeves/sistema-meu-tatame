package com.web.meutatame.tcc.controllers;


import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.repository.AlunoRepository;
import com.web.meutatame.tcc.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AlunosController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PresencaRepository presencaRepository;

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

    @Transactional
    @GetMapping("/alunos/{id}/excluir")
    public String excluir(@PathVariable int id) {
        if (alunoRepository.existsById(id)) {
            presencaRepository.deleteByAlunoId(id); // deleta pelas presenças com o ID
            alunoRepository.deleteById(id);         // deleta o aluno
        }

        return "redirect:/alunos";
    }


    @GetMapping("/alunos/{id}")
    public String buscar(@PathVariable int id, Model model){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        try{
            model.addAttribute("aluno", aluno.get());
        }
        catch(Exception err){ return "redirect:/alunos"; }

        return "alunos/editar";
    }

    @PostMapping("/alunos/{id}/atualizar")
    public String atualizar(@PathVariable int id, Aluno aluno){
        // if(!repo.exist(id)){
        if(!alunoRepository.existsById(id)){
            return "redirect:/alunos";
        }
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
}
