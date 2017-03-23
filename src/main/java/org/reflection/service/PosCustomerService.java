package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosCustomerNotFoundException;
import org.reflection.model.pos.PosCustomer;

import java.math.BigInteger;

public interface PosCustomerService {

    public PosCustomer findByMobile(String mobile) throws PosCustomerNotFoundException;

    public PosCustomer findById(BigInteger id) throws PosCustomerNotFoundException;

    public PosCustomer create(PosCustomer posCustomer);

    public PosCustomer update(PosCustomer posCustomer) throws PosCustomerNotFoundException;

    public PosCustomer copy(PosCustomer posCustomer) throws PosCustomerNotFoundException;

    public PosCustomer delete(BigInteger id) throws PosCustomerNotFoundException;

    public Iterable<PosCustomer> search(_SearchDTO pageable);

    public Iterable<PosCustomer> findAll(_SearchDTO pageable);

    public Iterable<PosCustomer> findAll();
}