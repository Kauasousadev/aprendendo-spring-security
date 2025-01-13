package edu.kaua.aprendendo_spring_security.repository;

import edu.kaua.aprendendo_spring_security.domain.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductUnitRepository extends JpaRepository<ProductUnit, UUID> {
}
