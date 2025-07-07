package com.web.meutatame.tcc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name= "turma")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "turma")
    private TurmaEnum turma;

    private String horario;

    @Column(name = "dias_semana")
    private String diasSemana;

    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;
}
