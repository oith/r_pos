package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosAdjustmentPurchaseReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentPurchaseReturnMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosAdjustmentPurchaseReturnMasterRepository extends JpaRepository<PosAdjustmentPurchaseReturnMaster, BigInteger> {
    public PosAdjustmentPurchaseReturnMaster findByCode(String code) throws PosAdjustmentPurchaseReturnMasterNotFoundException;
}
