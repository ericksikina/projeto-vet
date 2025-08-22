package com.bellapet.tipoProduto.persistence.entity;

import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Status status;
}
