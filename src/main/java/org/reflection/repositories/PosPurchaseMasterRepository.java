package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosPurchaseMasterNotFoundException;
import org.reflection.model.pos.PosPurchaseMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosPurchaseMasterRepository extends JpaRepository<PosPurchaseMaster, BigInteger> {
    public PosPurchaseMaster findByCode(String code) throws PosPurchaseMasterNotFoundException;
}
