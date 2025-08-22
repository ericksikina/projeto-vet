package com.bellapet.auth.persistence.entity;

import com.bellapet.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "auth")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Auth implements UserDetails {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String login;
    @Column(name = "senha")
    private String password;
    private UserRole role;

    public Auth(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
