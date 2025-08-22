package com.bellapet.pagamento.persistence.model;

import com.bellapet.pagamento.persistence.model.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @Column(name = "tipo")
    private TipoPagamento tipoPagamento;
}
