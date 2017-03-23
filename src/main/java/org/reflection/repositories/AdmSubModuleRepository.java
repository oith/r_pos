package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmSubModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmSubModuleRepository extends JpaRepository<AdmSubModule, BigInteger> {

}
