package edu.kaua.aprendendo_spring_security.service;

import edu.kaua.aprendendo_spring_security.domain.Users;
import edu.kaua.aprendendo_spring_security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users salvarUsuario(String username, String rawPassword, Users.Role role) {
        Users usuario = new Users();
        usuario.setLogin(username);
        usuario.setPassword(passwordEncoder.encode(rawPassword));
        usuario.setRole(role);

        return usuarioRepository.save(usuario);
    }
}
