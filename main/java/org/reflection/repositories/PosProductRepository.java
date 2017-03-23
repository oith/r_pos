package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosProductNotFoundException;
import org.reflection.model.pos.PosProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosProductRepository extends JpaRepository<PosProduct, BigInteger> {
    public PosProduct findByCode(String code) throws PosProductNotFoundException;
}
