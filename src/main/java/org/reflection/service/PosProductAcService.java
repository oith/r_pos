package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosProductAcNotFoundException;
import org.reflection.model.pos.PosProductAc;

import java.math.BigInteger;

public interface PosProductAcService {

    public PosProductAc findById(BigInteger id) throws PosProductAcNotFoundException;

    public PosProductAc create(PosProductAc posProductAc);

    public PosProductAc update(PosProductAc posProductAc) throws PosProductAcNotFoundException;

    public PosProductAc copy(PosProductAc posProductAc) throws PosProductAcNotFoundException;

    public PosProductAc delete(BigInteger id) throws PosProductAcNotFoundException;

    public Iterable<PosProductAc> search(_SearchDTO pageable);

    public Iterable<PosProductAc> findAll(_SearchDTO pageable);

    public Iterable<PosProductAc> findAll();
}