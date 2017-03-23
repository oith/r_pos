package org.reflection.service.auth;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.model.auth.AuthUser;

import java.math.BigInteger;

public interface AuthUserService {

    public AuthUser findByUsername(String username) throws AuthUserNotFoundException;

    public AuthUser findById(BigInteger id) throws AuthUserNotFoundException;

    public AuthUser create(AuthUser authUser);

    public AuthUser update(AuthUser authUser) throws AuthUserNotFoundException;

    public AuthUser copy(AuthUser authUser) throws AuthUserNotFoundException;

    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException;

    public Iterable<AuthUser> search(_SearchDTO pageable);

    public Iterable<AuthUser> findAll(_SearchDTO pageable);

    public Iterable<AuthUser> findAll();
}