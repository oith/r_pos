package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "POS_PURCHASE_MST")
public class PosPurchaseMaster extends AbstractTransactableAmount {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_SUPPLIER_ID", nullable = false)
    private PosSupplier posSupplier;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private Double paidAmount;

    @Transient
    private Double changeAmount;

    @OneToMany(mappedBy = "posPurchaseMaster")
    private Set<PosPurchaseDetail> posPurchaseDetails = new LinkedHashSet<>();

    public PosPurchaseMaster() {
    }

    public PosPurchaseMaster(PosSupplier posSupplier, String code, AbstractTransactableAmount abstractTransactableAmount, Double paidAmount) {
        super(abstractTransactableAmount);
        this.posSupplier = posSupplier;
        this.paidAmount = paidAmount;
    }

    public PosSupplier getPosSupplier() {
        return posSupplier;
    }

    public void setPosSupplier(PosSupplier posSupplier) {
        this.posSupplier = posSupplier;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Set<PosPurchaseDetail> getPosPurchaseDetails() {
        return posPurchaseDetails;
    }

    public void setPosPurchaseDetails(Set<PosPurchaseDetail> posPurchaseDetails) {
        this.posPurchaseDetails = posPurchaseDetails;
    }
}
