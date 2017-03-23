package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmLocationRepository extends JpaRepository<AdmLocation, BigInteger> {

}
