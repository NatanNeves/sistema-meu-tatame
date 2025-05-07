package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.dtos.TurmaDTO;
import com.web.meutatame.tcc.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/turmas")
    public String listarTurmas(Model model) {
        TurmaDTO turmaDTO = turmaService.listarAlunosPorIdade();

        // Adicionando os alunos por faixa etária no modelo
        model.addAttribute("kids1", turmaDTO.getKids1());
        model.addAttribute("kids2", turmaDTO.getKids2());
        model.addAttribute("kids3", turmaDTO.getKids3());
        model.addAttribute("adultos", turmaDTO.getAdultos());

        return "turmas/index";  // Vai para a página turmas.html
    }
}
