package com.web.meutatame.tcc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "frequencia")
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean presente;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "chamada_id")
    private Chamada chamada;

    public Frequencia(Aluno aluno, Chamada chamada, boolean presente) {
        this.aluno = aluno;
        this.chamada = chamada;
        this.presente = presente;
    }
}
