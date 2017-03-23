package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentDetailNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;

import java.math.BigInteger;

public interface PosAdjustmentDetailService {


    public PosAdjustmentDetail findById(BigInteger id) throws PosAdjustmentDetailNotFoundException;

    public PosAdjustmentDetail create(PosAdjustmentDetail posAdjustmentDetail);

    public PosAdjustmentDetail update(PosAdjustmentDetail posAdjustmentDetail) throws PosAdjustmentDetailNotFoundException;

    public PosAdjustmentDetail copy(PosAdjustmentDetail posAdjustmentDetail) throws PosAdjustmentDetailNotFoundException;

    public PosAdjustmentDetail delete(BigInteger id) throws PosAdjustmentDetailNotFoundException;

    public Iterable<PosAdjustmentDetail> search(_SearchDTO pageable);

    public Iterable<PosAdjustmentDetail> findAll(_SearchDTO pageable);

    public Iterable<PosAdjustmentDetail> findAll();
}