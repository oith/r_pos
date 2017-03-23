package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmModuleNotFoundException;
import org.reflection.model.com.AdmModule;

import java.math.BigInteger;

public interface AdmModuleService {


    public AdmModule findById(BigInteger id) throws AdmModuleNotFoundException;

    public AdmModule create(AdmModule admModule);

    public AdmModule update(AdmModule admModule) throws AdmModuleNotFoundException;

    public AdmModule copy(AdmModule admModule) throws AdmModuleNotFoundException;

    public AdmModule delete(BigInteger id) throws AdmModuleNotFoundException;

    public Iterable<AdmModule> search(_SearchDTO pageable);

    public Iterable<AdmModule> findAll(_SearchDTO pageable);

    public Iterable<AdmModule> findAll();
}