package com.web.meutatame.tcc.services;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.dtos.TurmaDTO;
import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            int idade = aluno.getIdade();

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
}
