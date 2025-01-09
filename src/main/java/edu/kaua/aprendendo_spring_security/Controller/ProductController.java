package edu.kaua.aprendendo_spring_security.Controller;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class ProductController {

    private final ProductRepository productRepository;

    // Injeção de dependência via construtor (recomendado)
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<String> newProduct(@RequestBody Product product) {
        // Validando entrada
        if (product == null || product.getProductName() == null) {
            return ResponseEntity.badRequest().body("Invalid product data");
        }

        productRepository.save(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}
