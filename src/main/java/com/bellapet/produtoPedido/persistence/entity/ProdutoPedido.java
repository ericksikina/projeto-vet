package com.bellapet.produtoPedido.persistence.entity;

import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.pedido.persistence.entity.Pedido;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qtde;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public ProdutoPedido(Integer qtde, Produto produto, Pedido pedido) {
        this.qtde = qtde;
        this.produto = produto;
        this.pedido = pedido;
    }
}
