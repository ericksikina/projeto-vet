package com.bellapet.produtoCarrinho.persistence.entity;

import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.produto.persistence.entity.Produto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto_carrinho")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtde;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    public ProdutoCarrinho(int qtde, Produto produto, Carrinho carrinho) {
        this.qtde = qtde;
        this.produto = produto;
        this.carrinho = carrinho;
    }
}
