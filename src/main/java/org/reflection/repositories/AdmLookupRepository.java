package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmLookupRepository extends JpaRepository<AdmLookup, BigInteger> {

}
