package com.bellapet.horario.persistence.repository;

import com.bellapet.horario.persistence.entity.Horario;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findAllByStatus(Status status);
}
