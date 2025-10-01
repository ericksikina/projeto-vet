package com.bellapet.agendamento.persistence.entity;

import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.agendamentoServico.persistence.entity.AgendamentoServico;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private StatusAgendamento status;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.PERSIST)
    private List<AgendamentoServico> listaDeServicos;
}
