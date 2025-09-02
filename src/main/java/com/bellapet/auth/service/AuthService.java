package com.bellapet.auth.service;

import com.bellapet.auth.http.adapter.AuthAdapter;
import com.bellapet.auth.http.request.AuthRequest;
import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.auth.persistence.respository.AuthRepository;
import com.bellapet.utils.enums.UserRole;
import com.bellapet.utils.exceptions.custom.InvalidRoleException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final AuthRepository authRepository;

    public String login(AuthRequest authRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((Auth) auth.getPrincipal());
    }

    public String loginComRole(AuthRequest authRequest, UserRole expectedRole) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password());

        var authentication = authenticationManager.authenticate(usernamePassword);
        Auth user = (Auth) authentication.getPrincipal();

        if (user.getRole() != expectedRole) {
            throw new InvalidRoleException("Usuário não tem permissão para acessar esta rota.");
        }

        return tokenService.generateToken(user);
    }

    public Auth cadastro(AuthRequest authRequest, UserRole userRole) {
        if(this.authRepository.findByLogin(authRequest.login()).isPresent())
            throw new IllegalArgumentException("Email de login já cadastrado!");

        Auth newAuthEntity = AuthAdapter.toAuth(authRequest, userRole);

        return this.authRepository.save(newAuthEntity);
    }
}
