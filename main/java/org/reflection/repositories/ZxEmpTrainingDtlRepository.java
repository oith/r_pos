package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxEmpTrainingDtl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxEmpTrainingDtlRepository extends JpaRepository<ZxEmpTrainingDtl, BigInteger> {

}
