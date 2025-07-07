package com.web.meutatame.tcc.repository;

import com.web.meutatame.tcc.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findAllByOrderByNomeAsc();

    @Query("SELECT a FROM Aluno a WHERE MONTH(a.dataNascimento) = :mesAtual")
    List<Aluno> findByMesAniversario(@Param("mesAtual") int mesAtual);
}
