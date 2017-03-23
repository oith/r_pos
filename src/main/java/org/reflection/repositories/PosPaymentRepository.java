package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosPaymentNotFoundException;
import org.reflection.model.pos.PosPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosPaymentRepository extends JpaRepository<PosPayment, BigInteger> {
    public PosPayment findByCode(String code) throws PosPaymentNotFoundException;
}
