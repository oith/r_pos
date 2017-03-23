package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPaymentNotFoundException;
import org.reflection.model.pos.PosPayment;

import java.math.BigInteger;

public interface PosPaymentService {

    public PosPayment findByCode(String code) throws PosPaymentNotFoundException;

    public PosPayment findById(BigInteger id) throws PosPaymentNotFoundException;

    public PosPayment create(PosPayment posPayment);

    public PosPayment update(PosPayment posPayment) throws PosPaymentNotFoundException;

    public PosPayment copy(PosPayment posPayment) throws PosPaymentNotFoundException;

    public PosPayment delete(BigInteger id) throws PosPaymentNotFoundException;

    public Iterable<PosPayment> search(_SearchDTO pageable);

    public Iterable<PosPayment> findAll(_SearchDTO pageable);

    public Iterable<PosPayment> findAll();
}