package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentPurchaseReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentPurchaseReturnMaster;

import java.math.BigInteger;

public interface PosAdjustmentPurchaseReturnMasterService {

    public PosAdjustmentPurchaseReturnMaster findByCode(String code) throws PosAdjustmentPurchaseReturnMasterNotFoundException;

    public PosAdjustmentPurchaseReturnMaster findById(BigInteger id) throws PosAdjustmentPurchaseReturnMasterNotFoundException;

    public PosAdjustmentPurchaseReturnMaster create(PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster);

    public PosAdjustmentPurchaseReturnMaster update(PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster) throws PosAdjustmentPurchaseReturnMasterNotFoundException;

    public PosAdjustmentPurchaseReturnMaster copy(PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster) throws PosAdjustmentPurchaseReturnMasterNotFoundException;

    public PosAdjustmentPurchaseReturnMaster delete(BigInteger id) throws PosAdjustmentPurchaseReturnMasterNotFoundException;

    public Iterable<PosAdjustmentPurchaseReturnMaster> search(_SearchDTO pageable);

    public Iterable<PosAdjustmentPurchaseReturnMaster> findAll(_SearchDTO pageable);

    public Iterable<PosAdjustmentPurchaseReturnMaster> findAll();
}