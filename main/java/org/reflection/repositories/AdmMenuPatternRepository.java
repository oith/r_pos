package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmMenuPattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmMenuPatternRepository extends JpaRepository<AdmMenuPattern, BigInteger> {

}
