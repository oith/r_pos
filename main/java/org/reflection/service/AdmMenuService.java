package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuNotFoundException;
import org.reflection.model.com.AdmMenu;

import java.math.BigInteger;

public interface AdmMenuService {

    public AdmMenu findByCode(String code) throws AdmMenuNotFoundException;

    public AdmMenu findById(BigInteger id) throws AdmMenuNotFoundException;

    public AdmMenu create(AdmMenu admMenu);

    public AdmMenu update(AdmMenu admMenu) throws AdmMenuNotFoundException;

    public AdmMenu copy(AdmMenu admMenu) throws AdmMenuNotFoundException;

    public AdmMenu delete(BigInteger id) throws AdmMenuNotFoundException;

    public Iterable<AdmMenu> search(_SearchDTO pageable);

    public Iterable<AdmMenu> findAll(_SearchDTO pageable);

    public Iterable<AdmMenu> findAll();
}