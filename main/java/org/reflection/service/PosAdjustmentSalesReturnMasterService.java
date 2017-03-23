package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentSalesReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentSalesReturnMaster;

import java.math.BigInteger;

public interface PosAdjustmentSalesReturnMasterService {

    public PosAdjustmentSalesReturnMaster findByCode(String code) throws PosAdjustmentSalesReturnMasterNotFoundException;

    public PosAdjustmentSalesReturnMaster findById(BigInteger id) throws PosAdjustmentSalesReturnMasterNotFoundException;

    public PosAdjustmentSalesReturnMaster create(PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster);

    public PosAdjustmentSalesReturnMaster update(PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster) throws PosAdjustmentSalesReturnMasterNotFoundException;

    public PosAdjustmentSalesReturnMaster copy(PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster) throws PosAdjustmentSalesReturnMasterNotFoundException;

    public PosAdjustmentSalesReturnMaster delete(BigInteger id) throws PosAdjustmentSalesReturnMasterNotFoundException;

    public Iterable<PosAdjustmentSalesReturnMaster> search(_SearchDTO pageable);

    public Iterable<PosAdjustmentSalesReturnMaster> findAll(_SearchDTO pageable);

    public Iterable<PosAdjustmentSalesReturnMaster> findAll();
}