package edu.kaua.aprendendo_spring_security.config;

import edu.kaua.aprendendo_spring_security.domain.Users;
import edu.kaua.aprendendo_spring_security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByLogin("ADMIN");

        userAdmin.ifPresentOrElse(
                (user)-> {
                    System.out.println("Admin já existe");
                },
                ()->{
                    var user = new Users();
                    user.setLogin("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("admin"));
                    user.setRole(Users.Role.valueOf("ADMIN"));
                    userRepository.save(user);
                }
        );
    }
}
