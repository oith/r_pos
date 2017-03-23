package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosSupplierNotFoundException;
import org.reflection.model.pos.PosSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosSupplierRepository extends JpaRepository<PosSupplier, BigInteger> {
    public PosSupplier findByMobile(String mobile) throws PosSupplierNotFoundException;
}
