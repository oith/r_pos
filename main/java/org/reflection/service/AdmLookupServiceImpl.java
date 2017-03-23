package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmLookupNotFoundException;
import org.reflection.model.com.AdmLookup;
import org.reflection.repositories.AdmLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admLookupService")
@Transactional(readOnly = true)
public class AdmLookupServiceImpl implements AdmLookupService {

    @Autowired
    private AdmLookupRepository admLookupRepository;


    @Transactional
    @Override
    public AdmLookup create(AdmLookup lookup) {
        return admLookupRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmLookupNotFoundException.class)
    @Override
    public AdmLookup update(AdmLookup updated) throws AdmLookupNotFoundException {
        AdmLookup admLookup = admLookupRepository.findOne(updated.getId());
        if (admLookup == null) {
            throw new AdmLookupNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admLookup);


        copyProperties(updated, admLookup);

        return admLookupRepository.save(admLookup);
    }

    @Transactional(rollbackFor = AdmLookupNotFoundException.class)
    @Override
    public AdmLookup copy(final AdmLookup copied) {

        AdmLookup admLookupOrginal = admLookupRepository.findOne(copied.getId());

        AdmLookup admLookup = new AdmLookup();
        //BeanUtils.copyProperties(copied, admLookup);
        //admLookup.setId(null);


        copyProperties(copied, admLookup);


        return admLookupRepository.save(admLookup);
    }

    private void copyProperties(AdmLookup from, AdmLookup to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setLookupKeyword(from.getLookupKeyword());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setRemarks(from.getRemarks());

    }

    @Override
    @Transactional
    public AdmLookup findById(BigInteger id) throws AdmLookupNotFoundException {
        AdmLookup admLookup = admLookupRepository.findOne(id);
        if (admLookup == null) {
            throw new AdmLookupNotFoundException();
        }

        return admLookup;
    }

    @Override
    @Transactional
    public Iterable<AdmLookup> findAll() {
        Iterable<AdmLookup> admLookups = admLookupRepository.findAll();

        for (AdmLookup admLookup : admLookups) {

        }

        return admLookups;
    }

    @Transactional
    @Override
    public Iterable<AdmLookup> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmLookup> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmLookupNotFoundException.class)
    public AdmLookup delete(BigInteger id) throws AdmLookupNotFoundException {

        AdmLookup admLookup = admLookupRepository.findOne(id);

        if (admLookup == null) {
            throw new AdmLookupNotFoundException();
        }
        admLookupRepository.delete(id);
        return admLookup;
    }
}