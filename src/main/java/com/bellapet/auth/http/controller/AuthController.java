package com.bellapet.auth.http.controller;

import com.bellapet.auth.http.request.AuthRequest;
import com.bellapet.auth.http.response.LoginResponse;
import com.bellapet.auth.persistence.respository.AuthRepository;
import com.bellapet.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Authorization")
@RequestMapping("/auth")
public class AuthController {
    private final AuthRepository authRepository;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(new LoginResponse(this.authService.login(authRequest)));
    }

//    @PostMapping("/cadastrar")
//    public ResponseEntity<Void> register(@RequestBody AuthRequest authRequest){
//        this.authService.cadastro(authRequest, UserRole.ADMIN);
//        return ResponseEntity.ok().build();
//    }
}
