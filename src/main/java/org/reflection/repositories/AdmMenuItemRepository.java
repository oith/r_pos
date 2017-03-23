package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.AdmMenuItemNotFoundException;
import org.reflection.model.com.AdmMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AdmMenuItemRepository extends JpaRepository<AdmMenuItem, BigInteger> {
    public AdmMenuItem findByCode(String code) throws AdmMenuItemNotFoundException;
}
