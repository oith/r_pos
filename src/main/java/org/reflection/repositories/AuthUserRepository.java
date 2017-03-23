package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.model.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthUserRepository extends JpaRepository<AuthUser, BigInteger> {
    public AuthUser findByUsername(String username) throws AuthUserNotFoundException;
}
