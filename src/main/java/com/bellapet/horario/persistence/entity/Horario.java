package com.bellapet.horario.persistence.entity;

import com.bellapet.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "horario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime horario;
    private Status status;
}
