package org.reflection.model.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_ADJUSTMENT_SR_MST")
public class PosAdjustmentSalesReturnMaster extends PosAdjustmentMaster {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_Sales_MST_ID", nullable = false)
    private PosSalesMaster posSalesMaster;

    public PosAdjustmentSalesReturnMaster() {
    }

    public PosSalesMaster getPosSalesMaster() {
        return posSalesMaster;
    }

    public void setPosSalesMaster(PosSalesMaster posSalesMaster) {
        this.posSalesMaster = posSalesMaster;
    }
}
