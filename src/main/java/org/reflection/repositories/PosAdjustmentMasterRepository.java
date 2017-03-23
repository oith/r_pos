package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosAdjustmentMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosAdjustmentMasterRepository extends JpaRepository<PosAdjustmentMaster, BigInteger> {
    public PosAdjustmentMaster findByCode(String code) throws PosAdjustmentMasterNotFoundException;
}
