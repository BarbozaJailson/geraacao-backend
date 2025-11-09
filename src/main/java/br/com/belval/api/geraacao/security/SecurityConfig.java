package br.com.belval.api.geraacao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login e registro
                        // Exemplo: permitir acesso às imagens sem autenticação
                        .requestMatchers("/**").permitAll() // login e registro
                        .requestMatchers("/uploads/**").hasRole("ADMIN_N1, ADMIN_N2, GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/instituicoes/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/usuario/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/requisicoes/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/item/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/doacoes/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers(HttpMethod.GET, "/api/campanha/**").hasAnyRole("ADMIN_N1", "ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/instituicoes/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/usuario/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/requisicoes/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/item/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/doacoes/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .requestMatchers("/api/campanha/**").hasAnyRole("ADMIN_N2", "GERENCIADOR")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // <- Tratamento de token inválido/expirado
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // <- sem sessão, só JWT
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

