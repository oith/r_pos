package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmLookupNotFoundException;
import org.reflection.model.com.AdmLookup;

import java.math.BigInteger;

public interface AdmLookupService {


    public AdmLookup findById(BigInteger id) throws AdmLookupNotFoundException;

    public AdmLookup create(AdmLookup admLookup);

    public AdmLookup update(AdmLookup admLookup) throws AdmLookupNotFoundException;

    public AdmLookup copy(AdmLookup admLookup) throws AdmLookupNotFoundException;

    public AdmLookup delete(BigInteger id) throws AdmLookupNotFoundException;

    public Iterable<AdmLookup> search(_SearchDTO pageable);

    public Iterable<AdmLookup> findAll(_SearchDTO pageable);

    public Iterable<AdmLookup> findAll();
}