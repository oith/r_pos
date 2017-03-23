package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosWarehouseNotFoundException;
import org.reflection.model.pos.PosWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosWarehouseRepository extends JpaRepository<PosWarehouse, BigInteger> {
    public PosWarehouse findByCode(String code) throws PosWarehouseNotFoundException;
}
