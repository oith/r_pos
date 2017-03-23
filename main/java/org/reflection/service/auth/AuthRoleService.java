package org.reflection.service.auth;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.model.auth.AuthRole;

import java.math.BigInteger;

public interface AuthRoleService {


    public AuthRole findById(BigInteger id) throws AuthRoleNotFoundException;

    public AuthRole create(AuthRole authRole);

    public AuthRole update(AuthRole authRole) throws AuthRoleNotFoundException;

    public AuthRole copy(AuthRole authRole) throws AuthRoleNotFoundException;

    public AuthRole delete(BigInteger id) throws AuthRoleNotFoundException;

    public Iterable<AuthRole> search(_SearchDTO pageable);

    public Iterable<AuthRole> findAll(_SearchDTO pageable);

    public Iterable<AuthRole> findAll();
}