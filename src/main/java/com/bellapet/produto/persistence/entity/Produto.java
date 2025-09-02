package com.bellapet.produto.persistence.entity;

import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "preco")
    private BigDecimal preco;
    @Column(name = "qtde_estoque")
    private Integer qtdeEstoque;
    @Column(name = "qtde_minima")
    private Integer qtdeMinima;
    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoProduto tipoProduto;
    @Column(name = "status")
    private Status status;
    @Column(name = "foto")
    private byte[] foto;
}
