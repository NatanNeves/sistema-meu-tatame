package com.web.meutatame.tcc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chamadas")
public class Chamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @OneToMany(mappedBy = "chamada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) // AQUI
    private List<Presenca> presencas = new ArrayList<>();
}
