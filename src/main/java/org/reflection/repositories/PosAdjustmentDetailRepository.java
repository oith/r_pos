package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.pos.PosAdjustmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosAdjustmentDetailRepository extends JpaRepository<PosAdjustmentDetail, BigInteger> {

}
