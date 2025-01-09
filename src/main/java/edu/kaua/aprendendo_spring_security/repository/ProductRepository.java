package edu.kaua.aprendendo_spring_security.repository;

import edu.kaua.aprendendo_spring_security.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductName(String name);
    Product findByProductId(int id);
}
