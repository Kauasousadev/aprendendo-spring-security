package edu.kaua.aprendendo_spring_security.repository;

import edu.kaua.aprendendo_spring_security.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(int id);
    Optional<Users> findByLogin(String login);
    Optional<Users> findByRole(String role);
}
