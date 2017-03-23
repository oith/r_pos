package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuPatternNotFoundException;
import org.reflection.model.com.AdmMenuPattern;
import org.reflection.repositories.AdmMenuPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admMenuPatternService")
@Transactional(readOnly = true)
public class AdmMenuPatternServiceImpl implements AdmMenuPatternService {

    @Autowired
    private AdmMenuPatternRepository admMenuPatternRepository;


    @Transactional
    @Override
    public AdmMenuPattern create(AdmMenuPattern lookup) {
        return admMenuPatternRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmMenuPatternNotFoundException.class)
    @Override
    public AdmMenuPattern update(AdmMenuPattern updated) throws AdmMenuPatternNotFoundException {
        AdmMenuPattern admMenuPattern = admMenuPatternRepository.findOne(updated.getId());
        if (admMenuPattern == null) {
            throw new AdmMenuPatternNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admMenuPattern);


        copyProperties(updated, admMenuPattern);

        return admMenuPatternRepository.save(admMenuPattern);
    }

    @Transactional(rollbackFor = AdmMenuPatternNotFoundException.class)
    @Override
    public AdmMenuPattern copy(final AdmMenuPattern copied) {

        AdmMenuPattern admMenuPatternOrginal = admMenuPatternRepository.findOne(copied.getId());

        AdmMenuPattern admMenuPattern = new AdmMenuPattern();
        //BeanUtils.copyProperties(copied, admMenuPattern);
        //admMenuPattern.setId(null);


        copyProperties(copied, admMenuPattern);


        return admMenuPatternRepository.save(admMenuPattern);
    }

    private void copyProperties(AdmMenuPattern from, AdmMenuPattern to) {
        to.setAdmMenuCommon(from.getAdmMenuCommon());
        to.setMenuOrientation(from.getMenuOrientation());
        to.setSlNo(from.getSlNo());

    }

    @Override
    @Transactional
    public AdmMenuPattern findById(BigInteger id) throws AdmMenuPatternNotFoundException {
        AdmMenuPattern admMenuPattern = admMenuPatternRepository.findOne(id);
        if (admMenuPattern == null) {
            throw new AdmMenuPatternNotFoundException();
        }

        return admMenuPattern;
    }

    @Override
    @Transactional
    public Iterable<AdmMenuPattern> findAll() {
        Iterable<AdmMenuPattern> admMenuPatterns = admMenuPatternRepository.findAll();

        for (AdmMenuPattern admMenuPattern : admMenuPatterns) {

        }

        return admMenuPatterns;
    }

    @Transactional
    @Override
    public Iterable<AdmMenuPattern> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmMenuPattern> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmMenuPatternNotFoundException.class)
    public AdmMenuPattern delete(BigInteger id) throws AdmMenuPatternNotFoundException {

        AdmMenuPattern admMenuPattern = admMenuPatternRepository.findOne(id);

        if (admMenuPattern == null) {
            throw new AdmMenuPatternNotFoundException();
        }
        admMenuPatternRepository.delete(id);
        return admMenuPattern;
    }
}