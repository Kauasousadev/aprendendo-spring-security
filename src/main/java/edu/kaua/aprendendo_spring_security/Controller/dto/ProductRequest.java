package edu.kaua.aprendendo_spring_security.Controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @NotNull(message = "Product name is required!")
    @Size(max = 255, message = "Product name max size is 255 characters!")
    private String productName;

    @Size(max = 255, message = "Description max size is 255 characters!")
    private String productDescription;

    @PositiveOrZero(message = "Product price must be positive or zero!")
    private Double productPrice;

    @PositiveOrZero(message = "Product quantity must be positive or zero!")
    private int productQuantity;

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

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
