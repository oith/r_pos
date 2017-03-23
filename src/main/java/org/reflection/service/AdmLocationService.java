package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmLocationNotFoundException;
import org.reflection.model.com.AdmLocation;

import java.math.BigInteger;

public interface AdmLocationService {


    public AdmLocation findById(BigInteger id) throws AdmLocationNotFoundException;

    public AdmLocation create(AdmLocation admLocation);

    public AdmLocation update(AdmLocation admLocation) throws AdmLocationNotFoundException;

    public AdmLocation copy(AdmLocation admLocation) throws AdmLocationNotFoundException;

    public AdmLocation delete(BigInteger id) throws AdmLocationNotFoundException;

    public Iterable<AdmLocation> search(_SearchDTO pageable);

    public Iterable<AdmLocation> findAll(_SearchDTO pageable);

    public Iterable<AdmLocation> findAll();
}