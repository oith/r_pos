package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.auth.AuthRequestMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthRequestMapRepository extends JpaRepository<AuthRequestMap, BigInteger> {

}
