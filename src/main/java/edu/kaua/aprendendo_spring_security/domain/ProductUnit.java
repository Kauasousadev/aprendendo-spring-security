package edu.kaua.aprendendo_spring_security.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ProductUnit {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID barcode;

   @ManyToOne
   @JoinColumn(name = "product_Id")
   private Product product;

    public ProductUnit(Product product) {
        this.product = product;
    }

    public ProductUnit() {}

    public UUID getBarcode() {
        return barcode;
    }

    public void setBarcode(UUID barcode) {
        this.barcode = barcode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductUnit{" +
                "barcode=" + barcode +
                '}';
    }
}
