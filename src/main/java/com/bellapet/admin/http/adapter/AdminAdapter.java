package com.bellapet.admin.http.adapter;

import com.bellapet.admin.http.request.AdminRequest;
import com.bellapet.admin.http.response.AdminResponse;
import com.bellapet.admin.persistence.entity.Admin;
import com.bellapet.auth.persistence.entity.Auth;

public class AdminAdapter {
    public static Admin toAdmin(Admin admin, AdminRequest adminRequest, Auth auth) {
        admin.setNome(adminRequest.nome());
        admin.setCpf(adminRequest.cpf());
        admin.setAuth(auth);

        return admin;
    }

    public static AdminResponse toResponse(Admin admin) {
        return new AdminResponse(admin.getNome());
    }
}
