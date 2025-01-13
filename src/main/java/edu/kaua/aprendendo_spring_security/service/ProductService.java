package edu.kaua.aprendendo_spring_security.service;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

}
