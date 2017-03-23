package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.auth.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, BigInteger> {

}
