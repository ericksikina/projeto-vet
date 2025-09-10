package com.bellapet.admin.http.controller;

import com.bellapet.admin.http.request.AdminRequest;
import com.bellapet.admin.http.response.AdminResponse;
import com.bellapet.admin.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Administrador")
@RequestMapping(path = "/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping(path = "/buscar")
    public ResponseEntity<AdminResponse> buscarAdmin(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.adminService.buscarAdmin(httpServletRequest));
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarAdmin(@RequestBody AdminRequest adminRequest){
        this.adminService.cadastrarAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
