package com.web.meutatame.tcc.repository;

import com.web.meutatame.tcc.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findAllByOrderByNomeAsc();
}
