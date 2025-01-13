package edu.kaua.aprendendo_spring_security.Controller.dto;

import edu.kaua.aprendendo_spring_security.domain.Product;
import edu.kaua.aprendendo_spring_security.domain.ProductUnit;

import java.util.List;
import java.util.UUID;

public class ProductDTO {
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private List<UUID> unitBarcodes;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getProductPrice();
        this.unitBarcodes = product.getUnits().stream()
                .map(ProductUnit::getBarcode)
                .toList();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public List<UUID> getUnitBarcodes() {
        return unitBarcodes;
    }

    public void setUnitBarcodes(List<UUID> unitBarcodes) {
        this.unitBarcodes = unitBarcodes;
    }
}
