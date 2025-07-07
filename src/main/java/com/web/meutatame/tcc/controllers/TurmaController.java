package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/turmas")
    public String listarTurmas(@RequestParam(required = false) String grupo, Model model) {
        if (grupo == null) {
            // Só mostra a página com os botões (sem alunos)
            return "turmas/index";
        }

        // Quando a turma é informada, busca os alunos e detalhes
        var turmasMap = turmaService.separarAlunosPorTurma(turmaService.buscarTodosAlunos());
        List<Aluno> alunosDaTurma = turmasMap.getOrDefault(grupo, List.of());

        List<String> diasDaSemana = turmaService.obterDiasDaSemanaPorTurma(grupo);
        String horario = turmaService.obterHorarioPorTurma(grupo);

        model.addAttribute("alunos", alunosDaTurma);
        model.addAttribute("dias", diasDaSemana);
        model.addAttribute("horario", horario);
        model.addAttribute("turma", grupo);

        return "turmas/detalhes";
    }
}
