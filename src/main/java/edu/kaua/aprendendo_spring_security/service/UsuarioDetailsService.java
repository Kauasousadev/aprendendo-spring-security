package edu.kaua.aprendendo_spring_security.service;

import edu.kaua.aprendendo_spring_security.domain.Users;
import org.springframework.security.core.userdetails.User;
import edu.kaua.aprendendo_spring_security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Essa classe auxilia na autenticação do spring security, sendo usada na SecurityConfig
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsuarioDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users usuario = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(usuario.getRole()) // O atributo "role" deve conter valores como "USER" ou "ADMIN"
                .build();
    }
}
