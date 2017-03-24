package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "POS_ADJUSTMENT_MST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PosAdjustmentMaster extends PosAbstractMaster {

    @OneToMany(mappedBy = "posAdjustmentMaster")
    private Set<PosAdjustmentDetail> posAdjustmentDetails = new LinkedHashSet<>();

    public PosAdjustmentMaster() {
    }

    public PosAdjustmentMaster(String code, AbstractTransactableAmount abstractTransactableAmount) {
        //super(abstractTransactableAmount);
        this.setCode(code);
    }

    public Set<PosAdjustmentDetail> getPosAdjustmentDetails() {
        return posAdjustmentDetails;
    }

    public void setPosAdjustmentDetails(Set<PosAdjustmentDetail> posAdjustmentDetails) {
        this.posAdjustmentDetails = posAdjustmentDetails;
    }

}
