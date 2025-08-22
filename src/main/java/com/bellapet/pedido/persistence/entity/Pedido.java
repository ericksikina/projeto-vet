package com.bellapet.pedido.persistence.entity;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.pagamento.persistence.model.Pagamento;
import com.bellapet.produtoPedido.persistence.entity.ProdutoPedido;
import com.bellapet.pedido.persistence.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @Column(name = "status_pedido")
    private StatusPedido statusPedido;
    private BigDecimal total;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pagamento")
    private Pagamento pagamento;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ProdutoPedido> listaDeProdutos = new ArrayList<>();
}
