package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseMasterNotFoundException;
import org.reflection.model.pos.PosPurchaseMaster;

import java.math.BigInteger;

public interface PosPurchaseMasterService {

    public PosPurchaseMaster findByCode(String code) throws PosPurchaseMasterNotFoundException;

    public PosPurchaseMaster findById(BigInteger id) throws PosPurchaseMasterNotFoundException;

    public PosPurchaseMaster create(PosPurchaseMaster posPurchaseMaster);

    public PosPurchaseMaster update(PosPurchaseMaster posPurchaseMaster) throws PosPurchaseMasterNotFoundException;

    public PosPurchaseMaster copy(PosPurchaseMaster posPurchaseMaster) throws PosPurchaseMasterNotFoundException;

    public PosPurchaseMaster delete(BigInteger id) throws PosPurchaseMasterNotFoundException;

    public Iterable<PosPurchaseMaster> search(_SearchDTO pageable);

    public Iterable<PosPurchaseMaster> findAll(_SearchDTO pageable);

    public Iterable<PosPurchaseMaster> findAll();
}