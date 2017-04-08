package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.pos.EmPosAnalysisCode;
import org.reflection.model.pos.PosProductAc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PosProductAcRepository extends JpaRepository<PosProductAc, BigInteger> {
    public List<PosProductAc> findAllByEmPosAnalysisCode(EmPosAnalysisCode emPosAnalysisCode);
}
