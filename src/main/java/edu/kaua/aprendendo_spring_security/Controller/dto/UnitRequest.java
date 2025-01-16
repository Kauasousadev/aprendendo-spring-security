package edu.kaua.aprendendo_spring_security.Controller.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UnitRequest {

    @Size(min = 36, max = 36)
    private UUID unitId;

    @PositiveOrZero(message = "Invalid id")
    private int productId;

    @PositiveOrZero(message = "Unit quantity must be zero or positivity!")
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getUnitId() {
        return unitId;
    }

    public void setUnitId(UUID unitId) {
        this.unitId = unitId;
    }
}
