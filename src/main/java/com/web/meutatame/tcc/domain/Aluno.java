package com.web.meutatame.tcc.domain;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Integer idade;
    private Double peso;
    private String telefone;
    private String email;

    // Endereço detalhado
    private String enderecoRua;
    private String enderecoNumero;
    private String enderecoBairro;
    private String enderecoCidade;

    private String graduacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataGraduacao;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public Aluno() {
    }

    public Aluno(String nome, LocalDate dataNascimento, Integer idade, Double peso, String telefone, String email,
                 String enderecoRua, String enderecoNumero, String enderecoBairro, String enderecoCidade,
                 String graduacao, LocalDate dataGraduacao, String categoria, String observacoes) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.telefone = telefone;
        this.email = email;
        this.enderecoRua = enderecoRua;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.enderecoCidade = enderecoCidade;
        this.graduacao = graduacao;
        this.dataGraduacao = dataGraduacao;
        this.categoria = categoria;
        this.observacoes = observacoes;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        calcularIdade();
    }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEnderecoRua() { return enderecoRua; }
    public void setEnderecoRua(String enderecoRua) { this.enderecoRua = enderecoRua; }

    public String getEnderecoNumero() { return enderecoNumero; }
    public void setEnderecoNumero(String enderecoNumero) { this.enderecoNumero = enderecoNumero; }

    public String getEnderecoBairro() { return enderecoBairro; }
    public void setEnderecoBairro(String enderecoBairro) { this.enderecoBairro = enderecoBairro; }

    public String getEnderecoCidade() { return enderecoCidade; }
    public void setEnderecoCidade(String enderecoCidade) { this.enderecoCidade = enderecoCidade; }

    public String getGraduacao() { return graduacao; }
    public void setGraduacao(String graduacao) { this.graduacao = graduacao; }

    public LocalDate getDataGraduacao() { return dataGraduacao; }
    public void setDataGraduacao(LocalDate dataGraduacao) { this.dataGraduacao = dataGraduacao; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    // Método que calcula a idade a partir da dataNascimento
    private void calcularIdade() {
        if (this.dataNascimento != null) {
            this.idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
        } else {
            this.idade = null;
        }
    }

    // Atualiza a idade antes de inserir ou atualizar no banco
    @PrePersist
    @PreUpdate
    public void atualizarIdadeAntesDeSalvar() {
        calcularIdade();
    }
}
