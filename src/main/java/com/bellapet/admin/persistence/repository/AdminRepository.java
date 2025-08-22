package com.bellapet.admin.persistence.repository;

import com.bellapet.admin.persistence.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByCpf(String cpf);
}
