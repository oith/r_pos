package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessDetailNotFoundException;
import org.reflection.model.com.AdmProcessDetail;

import java.math.BigInteger;

public interface AdmProcessDetailService {


    public AdmProcessDetail findById(BigInteger id) throws AdmProcessDetailNotFoundException;

    public AdmProcessDetail create(AdmProcessDetail admProcessDetail);

    public AdmProcessDetail update(AdmProcessDetail admProcessDetail) throws AdmProcessDetailNotFoundException;

    public AdmProcessDetail copy(AdmProcessDetail admProcessDetail) throws AdmProcessDetailNotFoundException;

    public AdmProcessDetail delete(BigInteger id) throws AdmProcessDetailNotFoundException;

    public Iterable<AdmProcessDetail> search(_SearchDTO pageable);

    public Iterable<AdmProcessDetail> findAll(_SearchDTO pageable);

    public Iterable<AdmProcessDetail> findAll();
}