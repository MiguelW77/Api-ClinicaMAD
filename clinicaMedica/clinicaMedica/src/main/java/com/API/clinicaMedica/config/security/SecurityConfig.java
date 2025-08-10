package com.API.clinicaMedica.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // NÃO use "*" aqui se você enviar credentials: 'include'.
        config.setAllowedOrigins(Arrays.asList(
            "http://127.0.0.1:5500",   // Live Server (VSCode) ou onde está seu front
            "http://localhost:5500"    // alternativa, caso use localhost
        ));

        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "X-Requested-With"));
        config.setAllowCredentials(true); // ESSENCIAL para enviar cookies
        config.setExposedHeaders(Arrays.asList("Authorization")); // se futuramente expor token

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // usa o CorsConfigurationSource bean
            .csrf(csrf -> csrf.disable()) // para API dev. Em produção, reveja isto.
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .authorizeHttpRequests(auth -> auth
                // liberar endpoints públicos (login, assets, documentação)
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/me").authenticated()
                .requestMatchers("/pacientes/**").permitAll()
                .requestMatchers("/medicos").permitAll()
                // permitir preflight OPTIONS (normalmente já coberto, mas explícito é ok)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // o resto exige autenticação
                .anyRequest().authenticated()
                           
                );
            // você pode ativar httpBasic apenas temporariamente para debug, NÃO necessário para sessão:
            http.httpBasic(AbstractHttpConfigurer::disable); 
            

        return http.build();
    }
}

