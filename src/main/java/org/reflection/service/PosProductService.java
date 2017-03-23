package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosProductNotFoundException;
import org.reflection.model.pos.PosProduct;

import java.math.BigInteger;

public interface PosProductService {

    public PosProduct findByCode(String code) throws PosProductNotFoundException;

    public PosProduct findById(BigInteger id) throws PosProductNotFoundException;

    public PosProduct create(PosProduct posProduct);

    public PosProduct update(PosProduct posProduct) throws PosProductNotFoundException;

    public PosProduct copy(PosProduct posProduct) throws PosProductNotFoundException;

    public PosProduct delete(BigInteger id) throws PosProductNotFoundException;

    public Iterable<PosProduct> search(_SearchDTO pageable);

    public Iterable<PosProduct> findAll(_SearchDTO pageable);

    public Iterable<PosProduct> findAll();
}