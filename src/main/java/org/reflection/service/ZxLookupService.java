package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxLookupNotFoundException;
import org.reflection.model.sample.ZxLookup;

import java.math.BigInteger;

public interface ZxLookupService {


    public ZxLookup findById(BigInteger id) throws ZxLookupNotFoundException;

    public ZxLookup create(ZxLookup zxLookup);

    public ZxLookup update(ZxLookup zxLookup) throws ZxLookupNotFoundException;

    public ZxLookup copy(ZxLookup zxLookup) throws ZxLookupNotFoundException;

    public ZxLookup delete(BigInteger id) throws ZxLookupNotFoundException;

    public Iterable<ZxLookup> search(_SearchDTO pageable);

    public Iterable<ZxLookup> findAll(_SearchDTO pageable);

    public Iterable<ZxLookup> findAll();
}