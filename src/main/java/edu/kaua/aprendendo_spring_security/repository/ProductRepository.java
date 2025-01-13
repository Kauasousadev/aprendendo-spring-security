package edu.kaua.aprendendo_spring_security.repository;

import edu.kaua.aprendendo_spring_security.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String name);
    Optional<Product> findByProductId(int id);

    @Query(value = "SELECT SUM(product_price) as storageValue FROM product", nativeQuery = true)
    Double findStorageValue();
}
