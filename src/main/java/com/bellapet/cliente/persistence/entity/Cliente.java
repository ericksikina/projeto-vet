package com.bellapet.cliente.persistence.entity;

import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.endereco.persistence.entity.Endereco;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;
    @OneToOne
    @JoinColumn(name = "id_auth")
    private Auth auth;
    private Status status;
}
