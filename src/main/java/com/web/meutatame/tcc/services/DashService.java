package com.web.meutatame.tcc.services;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashService {

    @Autowired
    private AlunoRepository alunoRepository;

    public long contarTotalAlunos() {
        return alunoRepository.count();
    }

    // Busca aniversariantes do mês filtrando todos os alunos em memória
    public List<Aluno> buscarAniversariantesDoMes() {
        int mesAtual = LocalDate.now().getMonthValue();

        // Buscar todos os alunos (pode dar problema se banco for gigante, mas OK para academia)
        List<Aluno> todosAlunos = alunoRepository.findAll();

        // Filtrar os que têm dataNascimento != null e mês da dataNascimento == mesAtual
        List<Aluno> aniversariantesDoMes = todosAlunos.stream()
                .filter(a -> a.getDataNascimento() != null
                        && a.getDataNascimento().getMonthValue() == mesAtual)
                .collect(Collectors.toList());

        return aniversariantesDoMes;
    }
}
