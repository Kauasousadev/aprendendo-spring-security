package edu.kaua.aprendendo_spring_security.service;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.domain.ProductUnit;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import edu.kaua.aprendendo_spring_security.repository.ProductUnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductUnitRepository productUnitRepository;

    public ProductService(ProductRepository productRepository, ProductUnitRepository productUnitRepository) {
        this.productRepository = productRepository;
        this.productUnitRepository = productUnitRepository;
    }

    public Product newProduct(Product product) {
        if (product.getProductName() == null){
            throw new IllegalArgumentException("Product name cannot be null");
        }
        else if (product.getProductDescription().length() > 255){
            throw new IllegalArgumentException("Product description cannot be longer than 255 characters");
        }
        else{
            return productRepository.save(product);
        }
    }

    public boolean deleteProduct(int productId) {
        if (productRepository.findById(productId).isPresent()){
            productRepository.deleteById(productId);
            return true;
        }else{
            return false;
        }
    }

    public boolean updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setProductDescription(updatedProduct.getProductDescription());
            product.setProductPrice(updatedProduct.getProductPrice());
            productRepository.save(product);
            return true;
        }).orElse(false);
    }

    public boolean parcialUpdateProduct(int id, Map<String, Object> updates) {
        return productRepository.findById(id).map(product -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "productName":
                        if (value instanceof String) {
                            product.setProductName((String) value);
                        }
                        break;
                    case "productDescription":
                        if (value instanceof String) {
                            product.setProductDescription((String) value);
                        }
                        break;
                    case "productPrice":
                        if (value instanceof Double) {
                            product.setProductPrice((Double) value);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Campo não reconhecido: " + key);
                }
            });
            productRepository.save(product);
            return true;
        }).orElse(false);
    }

    public void addProductUnit(int quantity, Product product) {
        for (int i = 0; i < quantity; i++){
            ProductUnit productUnit = new ProductUnit(product);
            product.addUnits(productUnit);
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
