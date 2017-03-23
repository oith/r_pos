package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmParamRepository extends JpaRepository<AdmParam, BigInteger> {

}
