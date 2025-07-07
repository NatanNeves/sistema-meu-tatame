package com.web.meutatame.tcc.services;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.domain.Chamada;
import com.web.meutatame.tcc.domain.Frequencia;
import com.web.meutatame.tcc.repository.AlunoRepository;
import com.web.meutatame.tcc.repository.ChamadaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChamadaService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ChamadaRepository chamadaRepository;

    @Transactional(readOnly = true)
    public List<Chamada> listarChamadas() {
        List<Chamada> chamadas = chamadaRepository.findAll();
        // Força a carga das presenças se estiver com fetch LAZY
        chamadas.forEach(chamada -> chamada.getPresencas().size());
        return chamadas;
    }

    @Transactional
    public void registrarChamada(Map<String, String> params) {
        LocalDate data = LocalDate.parse(params.get("data"));
        Chamada chamada = new Chamada();
        chamada.setData(data);

        List<Frequencia> presencas = new ArrayList<>();
        for (Aluno aluno : alunoRepository.findAll()) {
            boolean presente = params.containsKey("presente_" + aluno.getId());

            Frequencia p = new Frequencia();
            p.setAluno(aluno);
            p.setPresente(presente);
            p.setChamada(chamada);

            presencas.add(p);
        }

        chamada.setPresencas(presencas);
        chamadaRepository.save(chamada);
    }

    public Chamada buscarPorId(Long id) {
        return chamadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));
    }

    public void excluirChamada(Long id) {
        Chamada chamada = chamadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada!"));
        chamadaRepository.delete(chamada);
    }

    public void atualizarPresencas(Long chamadaId, HttpServletRequest request) {
        Chamada chamada = chamadaRepository.findById(chamadaId)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));

        for (Frequencia presenca : chamada.getPresencas()) {
            String param = "presente_" + presenca.getAluno().getId();
            boolean presente = request.getParameter(param) != null;
            presenca.setPresente(presente);
        }

        chamadaRepository.save(chamada);
    }


}
