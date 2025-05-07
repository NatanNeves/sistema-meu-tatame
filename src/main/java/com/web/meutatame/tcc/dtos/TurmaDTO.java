package com.web.meutatame.tcc.dtos;

import com.web.meutatame.tcc.domain.Aluno;

import java.util.List;

public class TurmaDTO {

    private List<Aluno> kids1;   // Até 5 anos
    private List<Aluno> kids2;   // De 6 a 8 anos
    private List<Aluno> kids3;   // De 9 a 12 anos
    private List<Aluno> adultos; // 13 anos ou mais

    // Getters e Setters
    public List<Aluno> getKids1() {
        return kids1;
    }

    public void setKids1(List<Aluno> kids1) {
        this.kids1 = kids1;
    }

    public List<Aluno> getKids2() {
        return kids2;
    }

    public void setKids2(List<Aluno> kids2) {
        this.kids2 = kids2;
    }

    public List<Aluno> getKids3() {
        return kids3;
    }

    public void setKids3(List<Aluno> kids3) {
        this.kids3 = kids3;
    }

    public List<Aluno> getAdultos() {
        return adultos;
    }

    public void setAdultos(List<Aluno> adultos) {
        this.adultos = adultos;
    }
}
