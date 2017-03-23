package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmMenuNotFoundException;
import org.reflection.model.com.AdmMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmMenuRepository extends JpaRepository<AdmMenu, BigInteger> {
    public AdmMenu findByCode(String code) throws AdmMenuNotFoundException;
}
