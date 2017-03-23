package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxAnyObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxAnyObjectRepository extends JpaRepository<ZxAnyObject, BigInteger> {

}
