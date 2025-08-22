package com.bellapet.carrinho.persistence.entity;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrinho")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal total;
    @OneToOne(mappedBy = "carrinho")
    private Cliente cliente;
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.PERSIST)
    private List<ProdutoCarrinho> listaDeProdutos;

    public Carrinho(Cliente cliente) {
        this.total = BigDecimal.ZERO;
        this.cliente = cliente;
        this.listaDeProdutos = new ArrayList<>();
    }
}
