package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.model.sample.ZxEmp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxEmpRepository extends JpaRepository<ZxEmp, BigInteger> {
    public ZxEmp findByCode(String code) throws ZxEmpNotFoundException;
}
