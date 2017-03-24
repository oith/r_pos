package org.reflection.model.pos;

import org.reflection.model.com.AbstractTransactableAmount;

import javax.persistence.*;

@Entity
@Table(name = "POS_ABSTRACT_MST")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PosAbstractMaster extends AbstractTransactableAmount {

    @ManyToOne(optional = false)
    @JoinColumn(name = "POS_BRANCH_ID", nullable = false)
    private PosBranch posBranch;

    public PosAbstractMaster() {
    }

    public PosBranch getPosBranch() {
        return posBranch;
    }

    public void setPosBranch(PosBranch posBranch) {
        this.posBranch = posBranch;
    }
}
