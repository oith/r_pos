package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosWarehouseNotFoundException;
import org.reflection.model.pos.PosWarehouse;

import java.math.BigInteger;

public interface PosWarehouseService {

    public PosWarehouse findByCode(String code) throws PosWarehouseNotFoundException;

    public PosWarehouse findById(BigInteger id) throws PosWarehouseNotFoundException;

    public PosWarehouse create(PosWarehouse posWarehouse);

    public PosWarehouse update(PosWarehouse posWarehouse) throws PosWarehouseNotFoundException;

    public PosWarehouse copy(PosWarehouse posWarehouse) throws PosWarehouseNotFoundException;

    public PosWarehouse delete(BigInteger id) throws PosWarehouseNotFoundException;

    public Iterable<PosWarehouse> search(_SearchDTO pageable);

    public Iterable<PosWarehouse> findAll(_SearchDTO pageable);

    public Iterable<PosWarehouse> findAll();
}