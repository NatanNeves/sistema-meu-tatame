package com.web.meutatame.tcc.services;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.dtos.TurmaDTO;
import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    @Autowired
    private AlunoRepository alunoRepository;

    public TurmaDTO listarAlunosPorIdade() {
        List<Aluno> alunos = alunoRepository.findAll();

        List<Aluno> kids1 = new ArrayList<>();
        List<Aluno> kids2 = new ArrayList<>();
        List<Aluno> kids3 = new ArrayList<>();
        List<Aluno> adultos = new ArrayList<>();

        for (Aluno aluno : alunos) {
            Integer idadeObj = aluno.getIdade();
            if (idadeObj == null) {
                // Ignora alunos sem idade definida
                continue;
            }
            int idade = idadeObj;

            if (idade <= 5) {
                kids1.add(aluno);
            } else if (idade <= 8) {
                kids2.add(aluno);
            } else if (idade <= 12) {
                kids3.add(aluno);
            } else {
                adultos.add(aluno);
            }
        }

        // Criando e retornando o objeto TurmaDTO
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setKids1(kids1);
        turmaDTO.setKids2(kids2);
        turmaDTO.setKids3(kids3);
        turmaDTO.setAdultos(adultos);
        return turmaDTO;
    }

    public long contarTotalAlunos() {
        return alunoRepository.count();
    }

    public Map<String, List<Aluno>> separarAlunosPorTurma(List<Aluno> alunos) {
        // Usando Java Streams para agrupar por turma
        Map<String, List<Aluno>> turmas = alunos.stream()
                .collect(Collectors.groupingBy(aluno -> {
                    Integer idade = aluno.getIdade();
                    if (idade == null) return "Sem idade";

                    if (idade <= 5) {
                        return "kids1";
                    } else if (idade <= 8) {
                        return "kids2";
                    } else if (idade <= 12) {
                        return "kids3";
                    } else {
                        return "adultos";
                    }
                }));
        return turmas;
    }

    public List<String> obterDiasDaSemanaPorTurma(String grupo) {
        if (grupo == null) return List.of();

        switch (grupo.toLowerCase()) {
            case "kids1":
            case "kids2":
            case "kids3":
                return Arrays.asList("Segunda", "Quarta", "Sexta");
            case "adultos":
                return Arrays.asList("Segunda", "Terça", "Quarta", "Sexta", "Sábado");
            default:
                return List.of();
        }
    }

    public String obterHorarioPorTurma(String grupo) {
        if (grupo == null) return "";

        switch (grupo.toLowerCase()) {
            case "kids1":
            case "kids2":
                return "18:00";
            case "kids3":
                return "19:00";
            case "adultos":
                return "20:00";  // exemplo, ajuste conforme quiser
            default:
                return "";
        }
    }

    // Método para buscar todos os alunos cadastrados no banco
    public List<Aluno> buscarTodosAlunos() {
        return alunoRepository.findAll();
    }
}
