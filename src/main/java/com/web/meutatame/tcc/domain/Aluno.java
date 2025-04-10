package com.web.meutatame.tcc.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String graduacao;
    private Integer idade;
    private Double peso;
    private String telefone;

    // Construtor padrão
    public Aluno() {
    }

    // Construtor com todos os campos (opcional)
    public Aluno(String nome, String graduacao, Integer idade, Double peso, String telefone) {
        this.nome = nome;
        this.graduacao = graduacao;
        this.idade = idade;
        this.peso = peso;
        this.telefone = telefone;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(String graduacao) {
        this.graduacao = graduacao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
