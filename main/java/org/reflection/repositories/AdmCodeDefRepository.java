package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmCodeDefNotFoundException;
import org.reflection.model.com.AdmCodeDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmCodeDefRepository extends JpaRepository<AdmCodeDef, BigInteger> {
    public AdmCodeDef findByPojoClass(String pojoClass) throws AdmCodeDefNotFoundException;
}
