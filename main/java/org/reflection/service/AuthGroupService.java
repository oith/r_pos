package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthGroupNotFoundException;
import org.reflection.model.auth.AuthGroup;

import java.math.BigInteger;

public interface AuthGroupService {


    public AuthGroup findById(BigInteger id) throws AuthGroupNotFoundException;

    public AuthGroup create(AuthGroup authGroup);

    public AuthGroup update(AuthGroup authGroup) throws AuthGroupNotFoundException;

    public AuthGroup copy(AuthGroup authGroup) throws AuthGroupNotFoundException;

    public AuthGroup delete(BigInteger id) throws AuthGroupNotFoundException;

    public Iterable<AuthGroup> search(_SearchDTO pageable);

    public Iterable<AuthGroup> findAll(_SearchDTO pageable);

    public Iterable<AuthGroup> findAll();
}