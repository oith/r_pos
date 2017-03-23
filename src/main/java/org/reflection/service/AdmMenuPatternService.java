package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuPatternNotFoundException;
import org.reflection.model.com.AdmMenuPattern;

import java.math.BigInteger;

public interface AdmMenuPatternService {


    public AdmMenuPattern findById(BigInteger id) throws AdmMenuPatternNotFoundException;

    public AdmMenuPattern create(AdmMenuPattern admMenuPattern);

    public AdmMenuPattern update(AdmMenuPattern admMenuPattern) throws AdmMenuPatternNotFoundException;

    public AdmMenuPattern copy(AdmMenuPattern admMenuPattern) throws AdmMenuPatternNotFoundException;

    public AdmMenuPattern delete(BigInteger id) throws AdmMenuPatternNotFoundException;

    public Iterable<AdmMenuPattern> search(_SearchDTO pageable);

    public Iterable<AdmMenuPattern> findAll(_SearchDTO pageable);

    public Iterable<AdmMenuPattern> findAll();
}