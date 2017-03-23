package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmParamNotFoundException;
import org.reflection.model.com.AdmParam;
import org.reflection.repositories.AdmParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admParamService")
@Transactional(readOnly = true)
public class AdmParamServiceImpl implements AdmParamService {

    @Autowired
    private AdmParamRepository admParamRepository;


    @Transactional
    @Override
    public AdmParam create(AdmParam lookup) {
        return admParamRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    @Override
    public AdmParam update(AdmParam updated) throws AdmParamNotFoundException {
        AdmParam admParam = admParamRepository.findOne(updated.getId());
        if (admParam == null) {
            throw new AdmParamNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admParam);


        copyProperties(updated, admParam);

        return admParamRepository.save(admParam);
    }

    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    @Override
    public AdmParam copy(final AdmParam copied) {

        AdmParam admParamOrginal = admParamRepository.findOne(copied.getId());

        AdmParam admParam = new AdmParam();
        //BeanUtils.copyProperties(copied, admParam);
        //admParam.setId(null);


        copyProperties(copied, admParam);


        return admParamRepository.save(admParam);
    }

    private void copyProperties(AdmParam from, AdmParam to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setWidgetType(from.getWidgetType());
        to.setIsActive(from.getIsActive());
        to.setIsMandatory(from.getIsMandatory());
        to.setSlNo(from.getSlNo());
        to.setCmd(from.getCmd());
        to.setDefaultVal(from.getDefaultVal());
        to.setHelpText(from.getHelpText());

    }

    @Override
    @Transactional
    public AdmParam findById(BigInteger id) throws AdmParamNotFoundException {
        AdmParam admParam = admParamRepository.findOne(id);
        if (admParam == null) {
            throw new AdmParamNotFoundException();
        }

        return admParam;
    }

    @Override
    @Transactional
    public Iterable<AdmParam> findAll() {
        Iterable<AdmParam> admParams = admParamRepository.findAll();

        for (AdmParam admParam : admParams) {

        }

        return admParams;
    }

    @Transactional
    @Override
    public Iterable<AdmParam> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmParam> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmParamNotFoundException.class)
    public AdmParam delete(BigInteger id) throws AdmParamNotFoundException {

        AdmParam admParam = admParamRepository.findOne(id);

        if (admParam == null) {
            throw new AdmParamNotFoundException();
        }
        admParamRepository.delete(id);
        return admParam;
    }
}