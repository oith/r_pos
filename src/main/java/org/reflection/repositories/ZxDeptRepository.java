package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxDeptRepository extends JpaRepository<ZxDept, BigInteger> {

}
