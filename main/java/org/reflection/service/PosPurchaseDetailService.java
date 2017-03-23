package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseDetailNotFoundException;
import org.reflection.model.pos.PosPurchaseDetail;

import java.math.BigInteger;

public interface PosPurchaseDetailService {


    public PosPurchaseDetail findById(BigInteger id) throws PosPurchaseDetailNotFoundException;

    public PosPurchaseDetail create(PosPurchaseDetail posPurchaseDetail);

    public PosPurchaseDetail update(PosPurchaseDetail posPurchaseDetail) throws PosPurchaseDetailNotFoundException;

    public PosPurchaseDetail copy(PosPurchaseDetail posPurchaseDetail) throws PosPurchaseDetailNotFoundException;

    public PosPurchaseDetail delete(BigInteger id) throws PosPurchaseDetailNotFoundException;

    public Iterable<PosPurchaseDetail> search(_SearchDTO pageable);

    public Iterable<PosPurchaseDetail> findAll(_SearchDTO pageable);

    public Iterable<PosPurchaseDetail> findAll();
}