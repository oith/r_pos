package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosCustomerNotFoundException;
import org.reflection.model.pos.PosCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosCustomerRepository extends JpaRepository<PosCustomer, BigInteger> {
    public PosCustomer findByMobile(String mobile) throws PosCustomerNotFoundException;
}
