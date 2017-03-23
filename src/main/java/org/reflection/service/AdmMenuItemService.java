package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuItemNotFoundException;
import org.reflection.model.com.AdmMenuItem;

import java.math.BigInteger;

public interface AdmMenuItemService {

    public AdmMenuItem findByCode(String code) throws AdmMenuItemNotFoundException;

    public AdmMenuItem findById(BigInteger id) throws AdmMenuItemNotFoundException;

    public AdmMenuItem create(AdmMenuItem admMenuItem);

    public AdmMenuItem update(AdmMenuItem admMenuItem) throws AdmMenuItemNotFoundException;

    public AdmMenuItem copy(AdmMenuItem admMenuItem) throws AdmMenuItemNotFoundException;

    public AdmMenuItem delete(BigInteger id) throws AdmMenuItemNotFoundException;

    public Iterable<AdmMenuItem> search(_SearchDTO pageable);

    public Iterable<AdmMenuItem> findAll(_SearchDTO pageable);

    public Iterable<AdmMenuItem> findAll();
}