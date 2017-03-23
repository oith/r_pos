package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportDetailNotFoundException;
import org.reflection.model.com.AdmReportDetail;

import java.math.BigInteger;

public interface AdmReportDetailService {


    public AdmReportDetail findById(BigInteger id) throws AdmReportDetailNotFoundException;

    public AdmReportDetail create(AdmReportDetail admReportDetail);

    public AdmReportDetail update(AdmReportDetail admReportDetail) throws AdmReportDetailNotFoundException;

    public AdmReportDetail copy(AdmReportDetail admReportDetail) throws AdmReportDetailNotFoundException;

    public AdmReportDetail delete(BigInteger id) throws AdmReportDetailNotFoundException;

    public Iterable<AdmReportDetail> search(_SearchDTO pageable);

    public Iterable<AdmReportDetail> findAll(_SearchDTO pageable);

    public Iterable<AdmReportDetail> findAll();
}