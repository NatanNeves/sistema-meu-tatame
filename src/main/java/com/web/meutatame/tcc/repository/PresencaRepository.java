package com.web.meutatame.tcc.repository;

import com.web.meutatame.tcc.domain.Aluno;
import com.web.meutatame.tcc.domain.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Presenca p WHERE p.aluno.id = :alunoId")
    void deleteByAlunoId(int alunoId);
}
