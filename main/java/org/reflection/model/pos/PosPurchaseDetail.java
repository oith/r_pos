package org.reflection.model.pos;

import org.reflection.model.com.Abstract;
import org.reflection.model.com.IDetail;
import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;

@Entity
@Table(name = "POS_PURCHASE_DTL")
public class PosPurchaseDetail extends Abstract implements IDetail {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PURCHASE_MST_ID", nullable = false)
    private PosPurchaseMaster posPurchaseMaster;

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

    public PosPurchaseDetail() {
    }

    public PosPurchaseDetail(PosPurchaseMaster posPurchaseMaster, PosProduct posProduct, Double quantity, Double unitPrice) {
        this.posPurchaseMaster = posPurchaseMaster;
        this.posProduct = posProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public PosPurchaseMaster getPosPurchaseMaster() {
        return posPurchaseMaster;
    }

    public void setPosPurchaseMaster(PosPurchaseMaster posPurchaseMaster) {
        this.posPurchaseMaster = posPurchaseMaster;
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
