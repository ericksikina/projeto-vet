package com.bellapet.servico.persistence.entity;

import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servico")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Status status;

    public Servico(String nome, String descricao, BigDecimal preco, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.status = status;
    }
}
