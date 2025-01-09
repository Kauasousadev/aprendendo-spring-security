package edu.kaua.aprendendo_spring_security.config;

import edu.kaua.aprendendo_spring_security.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService, PasswordEncoder passwordEncoder) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar os testes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home").permitAll() // Permite acesso público ao /home
                        .anyRequest().authenticated() // Requer autenticação para os demais endpoints
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/storage", true) // Redireciona após login bem-sucedido
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .httpBasic(httpBasic -> {}); // Configuração da autenticação básica

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
