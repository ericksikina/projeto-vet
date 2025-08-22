package com.bellapet.admin.service;

import com.bellapet.admin.http.adapter.AdminAdapter;
import com.bellapet.admin.http.request.AdminRequest;
import com.bellapet.admin.persistence.entity.Admin;
import com.bellapet.admin.persistence.repository.AdminRepository;
import com.bellapet.auth.http.request.AuthRequest;
import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.auth.service.AuthService;
import com.bellapet.utils.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AuthService authService;

    @Transactional
    public void cadastrarAdmin(AdminRequest adminRequest) {
        this.cpfJaCadastrado(adminRequest.cpf());
        Auth auth = this.authService
                .cadastro(new AuthRequest(adminRequest.login(), adminRequest.senha()), UserRole.ADMIN);
        this.adminRepository.save(AdminAdapter.toAdmin(new Admin(), adminRequest, auth));
    }

    private void cpfJaCadastrado(String cpf){
        Optional<Admin> optionalAdmin = this.adminRepository.findByCpf(cpf);
        if(optionalAdmin.isPresent())
            throw new IllegalArgumentException("Cpf já cadastrado!");
    }
}
