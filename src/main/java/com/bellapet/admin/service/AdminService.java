package com.bellapet.admin.service;

import com.bellapet.admin.http.adapter.AdminAdapter;
import com.bellapet.admin.http.request.AdminRequest;
import com.bellapet.admin.http.response.AdminResponse;
import com.bellapet.admin.persistence.entity.Admin;
import com.bellapet.admin.persistence.repository.AdminRepository;
import com.bellapet.auth.http.request.AuthRequest;
import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.auth.persistence.respository.AuthRepository;
import com.bellapet.auth.service.AuthService;
import com.bellapet.auth.service.TokenService;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.utils.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AuthService authService;
    private final TokenService tokenService;
    private final AuthRepository authRepository;

    public AdminResponse buscarAdmin(HttpServletRequest httpServletRequest) {
        return AdminAdapter.toResponse(this.buscarPorAuth(httpServletRequest));
    }

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

    public Admin buscarPorAuth(HttpServletRequest request) {
        String token = this.recuperarToken(request);
        String login = this.tokenService.getLoginFromToken(token);
        Auth auth = this.authRepository.findByLogin(login).get();

        return this.adminRepository.findByAuth(auth);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }
}
