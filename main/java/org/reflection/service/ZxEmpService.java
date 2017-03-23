package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.model.sample.ZxEmp;

import java.math.BigInteger;

public interface ZxEmpService {

    public ZxEmp findByCode(String code) throws ZxEmpNotFoundException;

    public ZxEmp findById(BigInteger id) throws ZxEmpNotFoundException;

    public ZxEmp create(ZxEmp zxEmp);

    public ZxEmp update(ZxEmp zxEmp) throws ZxEmpNotFoundException;

    public ZxEmp copy(ZxEmp zxEmp) throws ZxEmpNotFoundException;

    public ZxEmp delete(BigInteger id) throws ZxEmpNotFoundException;

    public Iterable<ZxEmp> search(_SearchDTO pageable);

    public Iterable<ZxEmp> findAll(_SearchDTO pageable);

    public Iterable<ZxEmp> findAll();
}