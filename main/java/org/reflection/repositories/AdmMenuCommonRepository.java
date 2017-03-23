package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmMenuCommonNotFoundException;
import org.reflection.model.com.AdmMenuCommon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmMenuCommonRepository extends JpaRepository<AdmMenuCommon, BigInteger> {
    public AdmMenuCommon findByCode(String code) throws AdmMenuCommonNotFoundException;
}
