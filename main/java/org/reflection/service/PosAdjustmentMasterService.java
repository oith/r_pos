package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentMaster;

import java.math.BigInteger;

public interface PosAdjustmentMasterService {

    public PosAdjustmentMaster findByCode(String code) throws PosAdjustmentMasterNotFoundException;

    public PosAdjustmentMaster findById(BigInteger id) throws PosAdjustmentMasterNotFoundException;

    public PosAdjustmentMaster create(PosAdjustmentMaster posAdjustmentMaster);

    public PosAdjustmentMaster update(PosAdjustmentMaster posAdjustmentMaster) throws PosAdjustmentMasterNotFoundException;

    public PosAdjustmentMaster copy(PosAdjustmentMaster posAdjustmentMaster) throws PosAdjustmentMasterNotFoundException;

    public PosAdjustmentMaster delete(BigInteger id) throws PosAdjustmentMasterNotFoundException;

    public Iterable<PosAdjustmentMaster> search(_SearchDTO pageable);

    public Iterable<PosAdjustmentMaster> findAll(_SearchDTO pageable);

    public Iterable<PosAdjustmentMaster> findAll();
}