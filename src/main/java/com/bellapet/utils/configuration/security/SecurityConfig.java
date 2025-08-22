package com.bellapet.utils.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources"
    };

    @Autowired
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers("/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/admin/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/animal/listar").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/animal/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/carrinho/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/cliente/listar").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/cliente/atualizar").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/cliente/atualizar-status").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/cliente/cadastrar").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/cliente/enviar-email-confirmacao").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/cliente/atualizar-senha").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/endereco/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/horario/listar").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/horario/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/pedido/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
