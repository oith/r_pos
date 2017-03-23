package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.auth.AuthUserEnvKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthUserEnvKeyRepository extends JpaRepository<AuthUserEnvKey, BigInteger> {

}
