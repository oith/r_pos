package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.com.AdmProcessDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmProcessDetailRepository extends JpaRepository<AdmProcessDetail, BigInteger> {

}
