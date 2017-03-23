package org.reflection.model.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_ADJUSTMENT_PR_MST")
public class PosAdjustmentPurchaseReturnMaster extends PosAdjustmentMaster {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PURCHASE_MST_ID", nullable = false)
    private PosPurchaseMaster posPurchaseMaster;

    public PosAdjustmentPurchaseReturnMaster() {
    }

    public PosPurchaseMaster getPosPurchaseMaster() {
        return posPurchaseMaster;
    }

    public void setPosPurchaseMaster(PosPurchaseMaster posPurchaseMaster) {
        this.posPurchaseMaster = posPurchaseMaster;
    }
}
