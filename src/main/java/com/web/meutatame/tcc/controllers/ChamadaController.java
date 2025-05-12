package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Chamada;
import com.web.meutatame.tcc.dtos.TurmaDTO;
import com.web.meutatame.tcc.services.ChamadaService;
import com.web.meutatame.tcc.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chamadas")
public class ChamadaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private ChamadaService chamadaService;

    @GetMapping
    public String listarChamadas(Model model) {
        List<Chamada> chamadas = chamadaService.listarChamadas(); // Certifique-se que as presenças estão carregadas
        model.addAttribute("chamadas", chamadas);
        model.addAttribute("data", LocalDate.now());
        return "chamada/index"; // View que será renderizada
    }

    @GetMapping("/nova")
    public String exibirFormularioChamada(Model model) {
        TurmaDTO turmaDTO = turmaService.listarAlunosPorIdade();
        model.addAttribute("turmaDTO", turmaDTO);  // Altere "turmas" para "turmaDTO"
        model.addAttribute("data", LocalDate.now());
        return "chamada/nova";
    }


    @PostMapping("/salvar")
    public String salvarChamada(@RequestParam Map<String, String> params) {
        chamadaService.registrarChamada(params);  // Salva a chamada com a presença dos alunos
        return "redirect:/chamadas"; // Redireciona para a lista de chamadas

    }

    @GetMapping("/detalhes/{id}")
    public String detalhesChamada(@PathVariable("id") Long id, Model model) {
        Chamada chamada = chamadaService.buscarPorId(id);
        model.addAttribute("chamada", chamada);
        return "chamada/detalhes"; // Aponta para o arquivo detalhes.html
    }

    @PostMapping("/excluir/{id}")
    public String excluirChamada(@PathVariable("id") Long id) {
        chamadaService.excluirChamada(id);
        return "redirect:/chamadas"; // Redireciona para a lista de chamadas após a exclusão
    }


}