package com.bellapet.animal.persistence.entity;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String raca;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    private Status status;
}
