package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosSalesMasterNotFoundException;
import org.reflection.model.pos.PosSalesMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosSalesMasterRepository extends JpaRepository<PosSalesMaster, BigInteger> {
    public PosSalesMaster findByCode(String code) throws PosSalesMasterNotFoundException;
}
