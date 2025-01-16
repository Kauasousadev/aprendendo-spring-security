package edu.kaua.aprendendo_spring_security.Controller;

import edu.kaua.aprendendo_spring_security.Controller.dto.ProductDTO;
import edu.kaua.aprendendo_spring_security.Controller.dto.ProductRequest;
import edu.kaua.aprendendo_spring_security.Controller.dto.UnitRequest;
import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.domain.ProductUnit;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import edu.kaua.aprendendo_spring_security.repository.ProductUnitRepository;
import edu.kaua.aprendendo_spring_security.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class ProductController {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private ProductUnitRepository productUnitRepository;

    // Injeção de dependência via construtor (recomendado)
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productService = new ProductService(productRepository, productUnitRepository);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newProduct(@Valid @RequestBody ProductRequest productRequest) {

        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getProductDescription(),
                productRequest.getProductPrice()
        );

        productService.addProductUnit(productRequest.getProductQuantity(), product);
        productService.newProduct(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    @PostMapping("/addUnit")
    public ResponseEntity<String> addUnit(@Valid @RequestBody UnitRequest unitRequest) {
        productService.addProductUnit(
                unitRequest.getQuantity(),
                productRepository.findByProductId(unitRequest.getProductId())
        );
        productRepository.save(productRepository.findByProductId(unitRequest.getProductId()));
        return ResponseEntity.ok("Unit added successfully");
    }

    @DeleteMapping("/deleteUnit")
    public ResponseEntity<String> deleteUnit(@Valid @RequestBody UnitRequest unitRequest) {
        productService.deleteProductUnit(unitRequest.getUnitId());
        return ResponseEntity.ok("Unit deleted successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(productDTOs);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable int id) {
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
