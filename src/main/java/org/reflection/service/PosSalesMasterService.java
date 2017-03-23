package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSalesMasterNotFoundException;
import org.reflection.model.pos.PosSalesMaster;

import java.math.BigInteger;

public interface PosSalesMasterService {

    public PosSalesMaster findByCode(String code) throws PosSalesMasterNotFoundException;

    public PosSalesMaster findById(BigInteger id) throws PosSalesMasterNotFoundException;

    public PosSalesMaster create(PosSalesMaster posSalesMaster);

    public PosSalesMaster update(PosSalesMaster posSalesMaster) throws PosSalesMasterNotFoundException;

    public PosSalesMaster copy(PosSalesMaster posSalesMaster) throws PosSalesMasterNotFoundException;

    public PosSalesMaster delete(BigInteger id) throws PosSalesMasterNotFoundException;

    public Iterable<PosSalesMaster> search(_SearchDTO pageable);

    public Iterable<PosSalesMaster> findAll(_SearchDTO pageable);

    public Iterable<PosSalesMaster> findAll();
}