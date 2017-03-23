package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSalesDetailNotFoundException;
import org.reflection.model.pos.PosSalesDetail;

import java.math.BigInteger;

public interface PosSalesDetailService {


    public PosSalesDetail findById(BigInteger id) throws PosSalesDetailNotFoundException;

    public PosSalesDetail create(PosSalesDetail posSalesDetail);

    public PosSalesDetail update(PosSalesDetail posSalesDetail) throws PosSalesDetailNotFoundException;

    public PosSalesDetail copy(PosSalesDetail posSalesDetail) throws PosSalesDetailNotFoundException;

    public PosSalesDetail delete(BigInteger id) throws PosSalesDetailNotFoundException;

    public Iterable<PosSalesDetail> search(_SearchDTO pageable);

    public Iterable<PosSalesDetail> findAll(_SearchDTO pageable);

    public Iterable<PosSalesDetail> findAll();
}