package edu.kaua.aprendendo_spring_security.Controller;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PatchMapping("/updateproduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setProductName(updatedProduct.getProductName());
                    product.setProductDescription(updatedProduct.getProductDescription());
                    product.setProductPrice(updatedProduct.getProductPrice());
                    productRepository.save(product);
                    return ResponseEntity.ok("Product updated successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/parcialupdate/{id}")
    public ResponseEntity<String> parcialUpdateProduct(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        return productRepository.findById(id)
                .map(product -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "productName": product.setProductName((String) value);
                            break;
                            case "productDescription": product.setProductDescription((String) value);
                            break;
                            case "productPrice": product.setProductPrice((Double) value);
                            break;
                        }
                    });
                    productRepository.save(product);
                    return ResponseEntity.ok("Product updated successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    };
}
