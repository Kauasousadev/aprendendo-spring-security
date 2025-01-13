package edu.kaua.aprendendo_spring_security.Controller;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import edu.kaua.aprendendo_spring_security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class ProductController {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductService productService;

    // Injeção de dependência via construtor (recomendado)
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productService = new ProductService(productRepository);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newProduct(@RequestBody Product product) {
        // Validando entrada
        if (product == null || product.getProductName() == null) {
            return ResponseEntity.badRequest().body("Invalid product data");
        }
        productService.newProduct(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok("Product deleted successfully");
        }else{
            return ResponseEntity.badRequest().body("Product not found");
        }
    }

    @PatchMapping("/updateproduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        if (productService.updateProduct(id, updatedProduct)) {
            return ResponseEntity.ok("Product updated successfully");
        }else{
            return ResponseEntity.badRequest().body("Product not found");
        }
    }

    @PutMapping("/parcialupdate/{id}")
    public ResponseEntity<String> parcialUpdateProduct(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        if (productService.parcialUpdateProduct(id, updates)) {
            return ResponseEntity.ok("Product updated successfully");
        }else{
            return ResponseEntity.badRequest().body("Product not found");
        }
    };

    @GetMapping("/StorageValue")
    public Double StorageValue() {
      return productRepository.findStorageValue();
    };
}
