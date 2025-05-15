package com.web.meutatame.tcc.services;


import com.web.meutatame.tcc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private AlunoRepository alunoRepository;

    public int contarAlunos() {
        return alunoRepository.findAll().size();
    }

    public double calcularLucroMensal() {
        return contarAlunos() * 120.0;
    }
}
