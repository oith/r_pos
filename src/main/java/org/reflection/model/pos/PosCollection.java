package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;

@Entity
@Table(name = "POS_COLLECTION")
public class PosCollection extends AbstractTransactableAmount {

    @ManyToOne
    @JoinColumn(name = "POS_SALES_MST_ID")
    private PosSalesMaster posSalesMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_CUSTOMER_ID", nullable = false)
    private PosCustomer posCustomer;

    @Enumerated(EnumType.STRING)
    @Column(name = "POS_COLLECTION_TYPE", nullable = false)
    private EmPosCollectionType emPosCollectionType;


    public PosCollection() {
    }

    public EmPosCollectionType getEmPosCollectionType() {
        return emPosCollectionType;
    }

    public void setEmPosCollectionType(EmPosCollectionType emPosCollectionType) {
        this.emPosCollectionType = emPosCollectionType;
    }

    public PosCustomer getPosCustomer() {
        return posCustomer;
    }

    public void setPosCustomer(PosCustomer posCustomer) {
        this.posCustomer = posCustomer;
    }

    public PosSalesMaster getPosSalesMaster() {
        return posSalesMaster;
    }

    public void setPosSalesMaster(PosSalesMaster posSalesMaster) {
        this.posSalesMaster = posSalesMaster;
    }
}
