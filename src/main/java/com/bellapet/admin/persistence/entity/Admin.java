package com.bellapet.admin.persistence.entity;

import com.bellapet.auth.persistence.entity.Auth;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @OneToOne
    @JoinColumn(name = "id_auth")
    private Auth auth;
}
