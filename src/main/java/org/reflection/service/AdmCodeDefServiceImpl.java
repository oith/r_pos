package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmCodeDefNotFoundException;
import org.reflection.model.com.AdmCodeDef;
import org.reflection.repositories.AdmCodeDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admCodeDefService")
@Transactional(readOnly = true)
public class AdmCodeDefServiceImpl implements AdmCodeDefService {

    @Autowired
    private AdmCodeDefRepository admCodeDefRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmCodeDef findByPojoClass(String pojoClass) throws AdmCodeDefNotFoundException {
        return admCodeDefRepository.findByPojoClass(pojoClass);
    }

    @Transactional
    @Override
    public AdmCodeDef create(AdmCodeDef lookup) {
        return admCodeDefRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmCodeDefNotFoundException.class)
    @Override
    public AdmCodeDef update(AdmCodeDef updated) throws AdmCodeDefNotFoundException {
        AdmCodeDef admCodeDef = admCodeDefRepository.findOne(updated.getId());
        if (admCodeDef == null) {
            throw new AdmCodeDefNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admCodeDef);


        copyProperties(updated, admCodeDef);

        return admCodeDefRepository.save(admCodeDef);
    }

    @Transactional(rollbackFor = AdmCodeDefNotFoundException.class)
    @Override
    public AdmCodeDef copy(final AdmCodeDef copied) {

        AdmCodeDef admCodeDefOrginal = admCodeDefRepository.findOne(copied.getId());

        AdmCodeDef admCodeDef = new AdmCodeDef();
        //BeanUtils.copyProperties(copied, admCodeDef);
        //admCodeDef.setId(null);


        copyProperties(copied, admCodeDef);


        return admCodeDefRepository.save(admCodeDef);
    }

    private void copyProperties(AdmCodeDef from, AdmCodeDef to) {
        to.setFullName(from.getFullName());
        to.setPojoClass(from.getPojoClass());
        to.setStartWith(from.getStartWith());
        to.setEndWith(from.getEndWith());
        to.setCodeLength(from.getCodeLength());
        to.setNextValue(from.getNextValue());

    }

    @Override
    @Transactional
    public AdmCodeDef findById(BigInteger id) throws AdmCodeDefNotFoundException {
        AdmCodeDef admCodeDef = admCodeDefRepository.findOne(id);
        if (admCodeDef == null) {
            throw new AdmCodeDefNotFoundException();
        }

        return admCodeDef;
    }

    @Override
    @Transactional
    public Iterable<AdmCodeDef> findAll() {
        Iterable<AdmCodeDef> admCodeDefs = admCodeDefRepository.findAll();

        for (AdmCodeDef admCodeDef : admCodeDefs) {

        }

        return admCodeDefs;
    }

    @Transactional
    @Override
    public Iterable<AdmCodeDef> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmCodeDef> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmCodeDefNotFoundException.class)
    public AdmCodeDef delete(BigInteger id) throws AdmCodeDefNotFoundException {

        AdmCodeDef admCodeDef = admCodeDefRepository.findOne(id);

        if (admCodeDef == null) {
            throw new AdmCodeDefNotFoundException();
        }
        admCodeDefRepository.delete(id);
        return admCodeDef;
    }
}