package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableQuantity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_INV_MOVEMENT")
public class PosInvMovement extends AbstractTransactableQuantity {


//    @ManyToOne
//    @JoinColumn(name = "POS_PURCHASE_MST_ID")
//    private PosPurchaseMaster posPurchaseMaster;
//
//    @ManyToOne
//    @JoinColumn(name = "POS_SALES_MST_ID")
//    private PosSalesMaster posSalesMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_PRODUCT_ID", nullable = false)
    private PosProduct posProduct;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_WAREHOUSE_From_ID", nullable = false)
    private PosWarehouse posWarehouseFrom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_WAREHOUSE_To_ID", nullable = false)
    private PosWarehouse posWarehouseTo;

    public PosInvMovement() {
    }

    public PosInvMovement(PosSupplier posSupplier, String code, AbstractTransactableQuantity abstractTransactableQuantity, Double paidAmount) {
        super(abstractTransactableQuantity);
    }

    public PosProduct getPosProduct() {
        return posProduct;
    }

    public void setPosProduct(PosProduct posProduct) {
        this.posProduct = posProduct;
    }


    public PosWarehouse getPosWarehouseFrom() {
        return posWarehouseFrom;
    }

    public void setPosWarehouseFrom(PosWarehouse posWarehouseFrom) {
        this.posWarehouseFrom = posWarehouseFrom;
    }

    public PosWarehouse getPosWarehouseTo() {
        return posWarehouseTo;
    }

    public void setPosWarehouseTo(PosWarehouse posWarehouseTo) {
        this.posWarehouseTo = posWarehouseTo;
    }
}
