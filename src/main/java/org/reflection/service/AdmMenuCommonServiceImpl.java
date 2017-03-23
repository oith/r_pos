package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuCommonNotFoundException;
import org.reflection.model.com.AdmMenuCommon;
import org.reflection.repositories.AdmMenuCommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admMenuCommonService")
@Transactional(readOnly = true)
public class AdmMenuCommonServiceImpl implements AdmMenuCommonService {

    @Autowired
    private AdmMenuCommonRepository admMenuCommonRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmMenuCommon findByCode(String code) throws AdmMenuCommonNotFoundException {
        return admMenuCommonRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmMenuCommon create(AdmMenuCommon lookup) {
        return admMenuCommonRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmMenuCommonNotFoundException.class)
    @Override
    public AdmMenuCommon update(AdmMenuCommon updated) throws AdmMenuCommonNotFoundException {
        AdmMenuCommon admMenuCommon = admMenuCommonRepository.findOne(updated.getId());
        if (admMenuCommon == null) {
            throw new AdmMenuCommonNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admMenuCommon);


        copyProperties(updated, admMenuCommon);

        return admMenuCommonRepository.save(admMenuCommon);
    }

    @Transactional(rollbackFor = AdmMenuCommonNotFoundException.class)
    @Override
    public AdmMenuCommon copy(final AdmMenuCommon copied) {

        AdmMenuCommon admMenuCommonOrginal = admMenuCommonRepository.findOne(copied.getId());

        AdmMenuCommon admMenuCommon = new AdmMenuCommon();
        //BeanUtils.copyProperties(copied, admMenuCommon);
        //admMenuCommon.setId(null);


        copyProperties(copied, admMenuCommon);


        return admMenuCommonRepository.save(admMenuCommon);
    }

    private void copyProperties(AdmMenuCommon from, AdmMenuCommon to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());
        to.setTooltip(from.getTooltip());
        to.setDisplayIconClass(from.getDisplayIconClass());
        to.setIsExternal(from.getIsExternal());
        to.setIsOpenInNewTab(from.getIsOpenInNewTab());
        to.setAdmSubModule(from.getAdmSubModule());

    }

    @Override
    @Transactional
    public AdmMenuCommon findById(BigInteger id) throws AdmMenuCommonNotFoundException {
        AdmMenuCommon admMenuCommon = admMenuCommonRepository.findOne(id);
        if (admMenuCommon == null) {
            throw new AdmMenuCommonNotFoundException();
        }

        return admMenuCommon;
    }

    @Override
    @Transactional
    public Iterable<AdmMenuCommon> findAll() {
        Iterable<AdmMenuCommon> admMenuCommons = admMenuCommonRepository.findAll();

        for (AdmMenuCommon admMenuCommon : admMenuCommons) {

        }

        return admMenuCommons;
    }

    @Transactional
    @Override
    public Iterable<AdmMenuCommon> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmMenuCommon> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmMenuCommonNotFoundException.class)
    public AdmMenuCommon delete(BigInteger id) throws AdmMenuCommonNotFoundException {

        AdmMenuCommon admMenuCommon = admMenuCommonRepository.findOne(id);

        if (admMenuCommon == null) {
            throw new AdmMenuCommonNotFoundException();
        }
        admMenuCommonRepository.delete(id);
        return admMenuCommon;
    }
}