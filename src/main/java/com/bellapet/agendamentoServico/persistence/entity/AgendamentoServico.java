package com.bellapet.agendamentoServico.persistence.entity;

import com.bellapet.agendamento.persistence.entity.Agendamento;
import com.bellapet.servico.persistence.entity.Servico;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento_servico")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AgendamentoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;
    @ManyToOne
    @JoinColumn(name = "id_agendamento")
    private Agendamento agendamento;

    public AgendamentoServico(Servico servico, Agendamento agendamento) {
        this.servico = servico;
        this.agendamento = agendamento;
    }
}
