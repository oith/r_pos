package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;

@Entity
@Table(name = "POS_PAYMENT")
public class PosPayment extends AbstractTransactableAmount {

    @ManyToOne
    @JoinColumn(name = "POS_PURCHASE_MST_ID")
    private PosPurchaseMaster posPurchaseMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_SUPPLIER_ID", nullable = false)
    private PosSupplier posSupplier;

    @Enumerated(EnumType.STRING)
    @Column(name = "POS_PAYMENT_TYPE", nullable = false)
    private EmPosPaymentType emPosPaymentType;


    public PosPayment() {
    }

    public EmPosPaymentType getEmPosPaymentType() {
        return emPosPaymentType;
    }

    public void setEmPosPaymentType(EmPosPaymentType emPosPaymentType) {
        this.emPosPaymentType = emPosPaymentType;
    }

    public PosSupplier getPosSupplier() {
        return posSupplier;
    }

    public void setPosSupplier(PosSupplier posSupplier) {
        this.posSupplier = posSupplier;
    }

    public PosPurchaseMaster getPosPurchaseMaster() {
        return posPurchaseMaster;
    }

    public void setPosPurchaseMaster(PosPurchaseMaster posPurchaseMaster) {
        this.posPurchaseMaster = posPurchaseMaster;
    }
}
