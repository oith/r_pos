package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxLookupRepository extends JpaRepository<ZxLookup, BigInteger> {

}
