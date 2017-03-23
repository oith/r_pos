package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxEmpEduDtl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxEmpEduDtlRepository extends JpaRepository<ZxEmpEduDtl, BigInteger> {

}
