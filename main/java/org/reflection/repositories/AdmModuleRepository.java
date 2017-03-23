package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmModuleRepository extends JpaRepository<AdmModule, BigInteger> {

}
