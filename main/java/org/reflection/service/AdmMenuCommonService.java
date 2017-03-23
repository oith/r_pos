package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuCommonNotFoundException;
import org.reflection.model.com.AdmMenuCommon;

import java.math.BigInteger;

public interface AdmMenuCommonService {

    public AdmMenuCommon findByCode(String code) throws AdmMenuCommonNotFoundException;

    public AdmMenuCommon findById(BigInteger id) throws AdmMenuCommonNotFoundException;

    public AdmMenuCommon create(AdmMenuCommon admMenuCommon);

    public AdmMenuCommon update(AdmMenuCommon admMenuCommon) throws AdmMenuCommonNotFoundException;

    public AdmMenuCommon copy(AdmMenuCommon admMenuCommon) throws AdmMenuCommonNotFoundException;

    public AdmMenuCommon delete(BigInteger id) throws AdmMenuCommonNotFoundException;

    public Iterable<AdmMenuCommon> search(_SearchDTO pageable);

    public Iterable<AdmMenuCommon> findAll(_SearchDTO pageable);

    public Iterable<AdmMenuCommon> findAll();
}