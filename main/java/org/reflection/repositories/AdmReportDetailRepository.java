package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmReportDetailRepository extends JpaRepository<AdmReportDetail, BigInteger> {

}
