package edu.kaua.aprendendo_spring_security.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_desc", length = 255)
    private String productDescription;

    @Column(name = "product_price")
    private double productPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductUnit> units;

    public Product(String productName, String productDescription, double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public Product() {}

    public void addUnits(ProductUnit unit) {
        if (units == null) {
            units = new ArrayList<>();
        }
        units.add(unit);
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

    public List<ProductUnit> getUnits() {
        return units;
    }

    public void setUnits(List<ProductUnit> units) {
        this.units = units;
    }
}
