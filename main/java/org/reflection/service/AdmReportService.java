package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.model.com.AdmReport;

import java.math.BigInteger;

public interface AdmReportService {

    public AdmReport findByCode(String code) throws AdmReportNotFoundException;

    public AdmReport findById(BigInteger id) throws AdmReportNotFoundException;

    public AdmReport create(AdmReport admReport);

    public AdmReport update(AdmReport admReport) throws AdmReportNotFoundException;

    public AdmReport copy(AdmReport admReport) throws AdmReportNotFoundException;

    public AdmReport delete(BigInteger id) throws AdmReportNotFoundException;

    public Iterable<AdmReport> search(_SearchDTO pageable);

    public Iterable<AdmReport> findAll(_SearchDTO pageable);

    public Iterable<AdmReport> findAll();
}