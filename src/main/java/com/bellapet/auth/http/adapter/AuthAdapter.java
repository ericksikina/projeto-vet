package com.bellapet.auth.http.adapter;

import com.bellapet.auth.http.request.AuthRequest;
import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.utils.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthAdapter {
    public static Auth toAuth(final AuthRequest registerRequest, UserRole userRole) {
        Auth auth = new Auth();
        auth.setLogin(registerRequest.login());
        auth.setPassword(new BCryptPasswordEncoder().encode(registerRequest.password()));
        auth.setRole(userRole);

        return auth;
    }
}
