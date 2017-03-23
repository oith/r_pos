package org.reflection.model.pos;

import org.reflection.model.com.Abstract;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POS_OPENING_BAL")
public class PosOpenBalance extends Abstract {


    @Column(name = "ON_DATE", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date onDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PRODUCT_ID", nullable = false)
    private PosProduct posProduct;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "SIGN", length = 30, nullable = false)
    private EmPosSign emPosSign;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    public PosOpenBalance() {
    }

    public PosOpenBalance(Date onDate, PosProduct posProduct, Double quantity, EmPosSign emPosSign, Double amount) {
        this.onDate = onDate;
        this.posProduct = posProduct;
        this.quantity = quantity;
        this.emPosSign = emPosSign;
        this.amount = amount;
    }

    public EmPosSign getEmSign() {
        return emPosSign;
    }

    public void setEmSign(EmPosSign emPosSign) {
        this.emPosSign = emPosSign;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
