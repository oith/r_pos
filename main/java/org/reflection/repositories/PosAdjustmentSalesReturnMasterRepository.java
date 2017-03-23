package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosAdjustmentSalesReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentSalesReturnMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosAdjustmentSalesReturnMasterRepository extends JpaRepository<PosAdjustmentSalesReturnMaster, BigInteger> {
    public PosAdjustmentSalesReturnMaster findByCode(String code) throws PosAdjustmentSalesReturnMasterNotFoundException;
}
