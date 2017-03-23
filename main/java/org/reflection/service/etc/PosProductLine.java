package org.reflection.service.etc;

import org.reflection.model.pos.PosProduct;

import java.io.Serializable;

public class PosProductLine implements Serializable {
    private PosProduct posProduct;
    private Double unitPriceMin;
    private Double unitPriceMax;
    private Double unitPrice;
    private Double quantity;

    public PosProductLine() {
    }

    public PosProduct getPosProduct() {
        return posProduct;
    }

    public void setPosProduct(PosProduct posProduct) {
        this.posProduct = posProduct;
    }

    public Double getUnitPriceMin() {
        return unitPriceMin;
    }

    public void setUnitPriceMin(Double unitPriceMin) {
        this.unitPriceMin = unitPriceMin;
    }

    public Double getUnitPriceMax() {
        return unitPriceMax;
    }

    public void setUnitPriceMax(Double unitPriceMax) {
        this.unitPriceMax = unitPriceMax;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosProductLine that = (PosProductLine) o;

        return posProduct.getId().equals(that.posProduct.getId());

    }

    @Override
    public int hashCode() {
        return posProduct.getId().hashCode();
    }
}
