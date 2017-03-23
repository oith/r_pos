package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmSubModuleNotFoundException;
import org.reflection.model.com.AdmSubModule;

import java.math.BigInteger;

public interface AdmSubModuleService {


    public AdmSubModule findById(BigInteger id) throws AdmSubModuleNotFoundException;

    public AdmSubModule create(AdmSubModule admSubModule);

    public AdmSubModule update(AdmSubModule admSubModule) throws AdmSubModuleNotFoundException;

    public AdmSubModule copy(AdmSubModule admSubModule) throws AdmSubModuleNotFoundException;

    public AdmSubModule delete(BigInteger id) throws AdmSubModuleNotFoundException;

    public Iterable<AdmSubModule> search(_SearchDTO pageable);

    public Iterable<AdmSubModule> findAll(_SearchDTO pageable);

    public Iterable<AdmSubModule> findAll();
}