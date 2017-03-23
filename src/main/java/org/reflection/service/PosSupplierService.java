package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSupplierNotFoundException;
import org.reflection.model.pos.PosSupplier;

import java.math.BigInteger;

public interface PosSupplierService {

    public PosSupplier findByMobile(String mobile) throws PosSupplierNotFoundException;

    public PosSupplier findById(BigInteger id) throws PosSupplierNotFoundException;

    public PosSupplier create(PosSupplier posSupplier);

    public PosSupplier update(PosSupplier posSupplier) throws PosSupplierNotFoundException;

    public PosSupplier copy(PosSupplier posSupplier) throws PosSupplierNotFoundException;

    public PosSupplier delete(BigInteger id) throws PosSupplierNotFoundException;

    public Iterable<PosSupplier> search(_SearchDTO pageable);

    public Iterable<PosSupplier> findAll(_SearchDTO pageable);

    public Iterable<PosSupplier> findAll();
}