package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserEnvKeyNotFoundException;
import org.reflection.model.auth.AuthUserEnvKey;

import java.math.BigInteger;

public interface AuthUserEnvKeyService {


    public AuthUserEnvKey findById(BigInteger id) throws AuthUserEnvKeyNotFoundException;

    public AuthUserEnvKey create(AuthUserEnvKey authUserEnvKey);

    public AuthUserEnvKey update(AuthUserEnvKey authUserEnvKey) throws AuthUserEnvKeyNotFoundException;

    public AuthUserEnvKey copy(AuthUserEnvKey authUserEnvKey) throws AuthUserEnvKeyNotFoundException;

    public AuthUserEnvKey delete(BigInteger id) throws AuthUserEnvKeyNotFoundException;

    public Iterable<AuthUserEnvKey> search(_SearchDTO pageable);

    public Iterable<AuthUserEnvKey> findAll(_SearchDTO pageable);

    public Iterable<AuthUserEnvKey> findAll();
}