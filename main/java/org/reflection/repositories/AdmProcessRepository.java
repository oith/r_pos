package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmProcessNotFoundException;
import org.reflection.model.com.AdmProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmProcessRepository extends JpaRepository<AdmProcess, BigInteger> {
    public AdmProcess findByCode(String code) throws AdmProcessNotFoundException;
}
