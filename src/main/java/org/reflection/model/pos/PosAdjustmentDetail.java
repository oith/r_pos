package org.reflection.model.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_ADJUSTMENT_DTL")
public class PosAdjustmentDetail extends PosAbstractDetail {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_ADJUSTMENT_MST_ID", nullable = false)
    private PosAdjustmentMaster posAdjustmentMaster;

    public PosAdjustmentDetail() {
    }

    public PosAdjustmentDetail(PosAdjustmentMaster posAdjustmentMaster, PosProduct posProduct, Double quantity, Double unitPrice) {
        super(posProduct, quantity, unitPrice);
        this.posAdjustmentMaster = posAdjustmentMaster;
    }

    public PosAdjustmentMaster getPosAdjustmentMaster() {
        return posAdjustmentMaster;
    }

    public void setPosAdjustmentMaster(PosAdjustmentMaster posAdjustmentMaster) {
        this.posAdjustmentMaster = posAdjustmentMaster;
    }
}
