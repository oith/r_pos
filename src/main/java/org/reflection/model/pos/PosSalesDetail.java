package org.reflection.model.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_SALES_DTL")
public class PosSalesDetail extends PosAbstractDetail {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_SALES_MST_ID", nullable = false)
    private PosSalesMaster posSalesMaster;

    public PosSalesDetail() {
    }

    public PosSalesDetail(PosSalesMaster posSalesMaster, PosProduct posProduct, Double quantity, Double unitPrice) {
        super(posProduct, quantity, unitPrice);
        this.posSalesMaster = posSalesMaster;
    }

    public PosSalesMaster getPosSalesMaster() {
        return posSalesMaster;
    }

    public void setPosSalesMaster(PosSalesMaster posSalesMaster) {
        this.posSalesMaster = posSalesMaster;
    }

}
