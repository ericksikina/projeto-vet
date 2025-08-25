package com.bellapet.agendamento.persistence.entity;

import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.cliente.persistence.entity.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    private StatusAgendamento status;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
