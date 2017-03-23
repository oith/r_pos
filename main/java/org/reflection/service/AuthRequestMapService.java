package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRequestMapNotFoundException;
import org.reflection.model.auth.AuthRequestMap;

import java.math.BigInteger;

public interface AuthRequestMapService {


    public AuthRequestMap findById(BigInteger id) throws AuthRequestMapNotFoundException;

    public AuthRequestMap create(AuthRequestMap authRequestMap);

    public AuthRequestMap update(AuthRequestMap authRequestMap) throws AuthRequestMapNotFoundException;

    public AuthRequestMap copy(AuthRequestMap authRequestMap) throws AuthRequestMapNotFoundException;

    public AuthRequestMap delete(BigInteger id) throws AuthRequestMapNotFoundException;

    public Iterable<AuthRequestMap> search(_SearchDTO pageable);

    public Iterable<AuthRequestMap> findAll(_SearchDTO pageable);

    public Iterable<AuthRequestMap> findAll();
}