package org.reflection.model.pos;

import org.reflection.model.com.Abstract;
import org.reflection.model.com.IDetail;
import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;

@Entity
@Table(name = "POS_SALES_DTL")
public class PosSalesDetail extends Abstract implements IDetail {


    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_SALES_MST_ID", nullable = false)
    private PosSalesMaster posSalesMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PRODUCT_ID", nullable = false)
    private PosProduct posProduct;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "LINE_TOTAL", nullable = false)
    private Double lineTotal;

    @Transient
    private CrudType crudType;

    public PosSalesDetail() {
    }

    public PosSalesDetail(PosSalesMaster posSalesMaster, PosProduct posProduct, Double quantity, Double unitPrice) {
        this.posSalesMaster = posSalesMaster;
        this.posProduct = posProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice * quantity;
    }

    public PosSalesMaster getPosSalesMaster() {
        return posSalesMaster;
    }

    public void setPosSalesMaster(PosSalesMaster posSalesMaster) {
        this.posSalesMaster = posSalesMaster;
    }

    public PosProduct getPosProduct() {
        return posProduct;
    }

    public void setPosProduct(PosProduct posProduct) {
        this.posProduct = posProduct;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public CrudType getCrudType() {
        return crudType;
    }

    @Override
    public void setCrudType(CrudType crudType) {
        this.crudType = crudType;
    }
}
