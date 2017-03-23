package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.model.com.AdmReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmReportRepository extends JpaRepository<AdmReport, BigInteger> {
    public AdmReport findByCode(String code) throws AdmReportNotFoundException;
}
