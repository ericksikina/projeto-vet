package com.bellapet.endereco.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String complemento;
}
