package com.bellapet.admin.http.controller;

import com.bellapet.admin.http.request.AdminRequest;
import com.bellapet.admin.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Administrador")
@RequestMapping(path = "/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarAdmin(@RequestBody AdminRequest adminRequest){
        this.adminService.cadastrarAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
