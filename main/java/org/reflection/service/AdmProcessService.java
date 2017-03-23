package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessNotFoundException;
import org.reflection.model.com.AdmProcess;

import java.math.BigInteger;

public interface AdmProcessService {

    public AdmProcess findByCode(String code) throws AdmProcessNotFoundException;

    public AdmProcess findById(BigInteger id) throws AdmProcessNotFoundException;

    public AdmProcess create(AdmProcess admProcess);

    public AdmProcess update(AdmProcess admProcess) throws AdmProcessNotFoundException;

    public AdmProcess copy(AdmProcess admProcess) throws AdmProcessNotFoundException;

    public AdmProcess delete(BigInteger id) throws AdmProcessNotFoundException;

    public Iterable<AdmProcess> search(_SearchDTO pageable);

    public Iterable<AdmProcess> findAll(_SearchDTO pageable);

    public Iterable<AdmProcess> findAll();
}