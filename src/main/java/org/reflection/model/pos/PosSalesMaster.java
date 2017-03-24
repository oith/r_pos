package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "POS_SALES_MST")
public class PosSalesMaster extends PosAbstractMaster {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_CUSTOMER_ID", nullable = false)
    private PosCustomer posCustomer;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private Double paidAmount;

    @Transient
    private Double changeAmount;

    @OneToMany(mappedBy = "posSalesMaster")
    private Set<PosSalesDetail> posSalesDetails = new LinkedHashSet();

//    @OneToMany(mappedBy = "posSalesMaster")
//    private Set<PosPayment> posPayments = new LinkedHashSet();

    public PosSalesMaster() {
    }

    public PosSalesMaster(PosCustomer posCustomer, AbstractTransactableAmount abstractTransactableAmount, Double paidAmount) {
       // super(abstractTransactableAmount);
        this.posCustomer = posCustomer;
        this.paidAmount = paidAmount;
    }

    public PosCustomer getPosCustomer() {
        return posCustomer;
    }

    public void setPosCustomer(PosCustomer posCustomer) {
        this.posCustomer = posCustomer;
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

    public Set<PosSalesDetail> getPosSalesDetails() {
        return posSalesDetails;
    }

    public void setPosSalesDetails(Set<PosSalesDetail> posSalesDetails) {
        this.posSalesDetails = posSalesDetails;
    }

}
