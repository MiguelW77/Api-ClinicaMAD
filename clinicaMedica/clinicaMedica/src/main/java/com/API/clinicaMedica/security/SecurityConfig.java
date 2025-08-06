package com.API.clinicaMedica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF (útil para testes de API)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite qualquer requisição
            )
            .httpBasic(Customizer.withDefaults()); // Habilita autenticação básica (pode ser removido, se não usar login)

        return http.build();
    }
}
