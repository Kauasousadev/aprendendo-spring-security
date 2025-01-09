package edu.kaua.aprendendo_spring_security.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users") // Nome da tabela no banco, alterado para evitar conflitos com palavras reservadas.
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Salva o nome do enum como string no banco.
    @Column(name = "role", nullable = false)
    private Role role;

    public Users(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Users() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN, USER
    }

    // Métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + this.role.name()); // Adiciona prefixo "ROLE_" necessário para Spring Security.
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Pode adicionar lógica específica aqui.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Pode adicionar lógica específica aqui.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Pode adicionar lógica específica aqui.
    }

    @Override
    public boolean isEnabled() {
        return true; // Pode adicionar lógica específica aqui.
    }
}
