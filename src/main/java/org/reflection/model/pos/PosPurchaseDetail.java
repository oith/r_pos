package org.reflection.model.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_PURCHASE_DTL")
public class PosPurchaseDetail extends PosAbstractDetail   {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PURCHASE_MST_ID", nullable = false)
    private PosPurchaseMaster posPurchaseMaster;

    public PosPurchaseDetail() {
    }

    public PosPurchaseDetail(PosPurchaseMaster posPurchaseMaster, PosProduct posProduct, Double quantity, Double unitPrice) {
        super(posProduct,quantity,unitPrice);
        this.posPurchaseMaster = posPurchaseMaster;
    }

    public PosPurchaseMaster getPosPurchaseMaster() {
        return posPurchaseMaster;
    }

    public void setPosPurchaseMaster(PosPurchaseMaster posPurchaseMaster) {
        this.posPurchaseMaster = posPurchaseMaster;
    }

}
