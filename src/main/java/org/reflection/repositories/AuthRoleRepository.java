package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthRoleRepository extends JpaRepository<AuthRole, BigInteger> {

}
