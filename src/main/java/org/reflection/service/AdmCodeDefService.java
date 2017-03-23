package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmCodeDefNotFoundException;
import org.reflection.model.com.AdmCodeDef;

import java.math.BigInteger;

public interface AdmCodeDefService {

    public AdmCodeDef findByPojoClass(String pojoClass) throws AdmCodeDefNotFoundException;

    public AdmCodeDef findById(BigInteger id) throws AdmCodeDefNotFoundException;

    public AdmCodeDef create(AdmCodeDef admCodeDef);

    public AdmCodeDef update(AdmCodeDef admCodeDef) throws AdmCodeDefNotFoundException;

    public AdmCodeDef copy(AdmCodeDef admCodeDef) throws AdmCodeDefNotFoundException;

    public AdmCodeDef delete(BigInteger id) throws AdmCodeDefNotFoundException;

    public Iterable<AdmCodeDef> search(_SearchDTO pageable);

    public Iterable<AdmCodeDef> findAll(_SearchDTO pageable);

    public Iterable<AdmCodeDef> findAll();
}