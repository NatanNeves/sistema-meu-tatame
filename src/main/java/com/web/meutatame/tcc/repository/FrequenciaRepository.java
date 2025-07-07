package com.web.meutatame.tcc.repository;

import com.web.meutatame.tcc.domain.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Frequencia p WHERE p.aluno.id = :alunoId")
    void deleteByAlunoId(int alunoId);
}
