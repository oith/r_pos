package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessDetailNotFoundException;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.repositories.AdmProcessDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admProcessDetailService")
@Transactional(readOnly = true)
public class AdmProcessDetailServiceImpl implements AdmProcessDetailService {

    @Autowired
    private AdmProcessDetailRepository admProcessDetailRepository;


    @Transactional
    @Override
    public AdmProcessDetail create(AdmProcessDetail lookup) {
        return admProcessDetailRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    @Override
    public AdmProcessDetail update(AdmProcessDetail updated) throws AdmProcessDetailNotFoundException {
        AdmProcessDetail admProcessDetail = admProcessDetailRepository.findOne(updated.getId());
        if (admProcessDetail == null) {
            throw new AdmProcessDetailNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admProcessDetail);


        copyProperties(updated, admProcessDetail);

        return admProcessDetailRepository.save(admProcessDetail);
    }

    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    @Override
    public AdmProcessDetail copy(final AdmProcessDetail copied) {

        AdmProcessDetail admProcessDetailOrginal = admProcessDetailRepository.findOne(copied.getId());

        AdmProcessDetail admProcessDetail = new AdmProcessDetail();
        //BeanUtils.copyProperties(copied, admProcessDetail);
        //admProcessDetail.setId(null);


        copyProperties(copied, admProcessDetail);


        return admProcessDetailRepository.save(admProcessDetail);
    }

    private void copyProperties(AdmProcessDetail from, AdmProcessDetail to) {
        to.setAdmProcess(from.getAdmProcess());
        to.setAdmParam(from.getAdmParam());
        to.setZoneType(from.getZoneType());
        to.setIsActive(from.getIsActive());
        to.setIsMandatory(from.getIsMandatory());
        to.setDefaultVal(from.getDefaultVal());
        to.setHelpText(from.getHelpText());
        to.setSlNo(from.getSlNo());

    }

    @Override
    @Transactional
    public AdmProcessDetail findById(BigInteger id) throws AdmProcessDetailNotFoundException {
        AdmProcessDetail admProcessDetail = admProcessDetailRepository.findOne(id);
        if (admProcessDetail == null) {
            throw new AdmProcessDetailNotFoundException();
        }

        return admProcessDetail;
    }

    @Override
    @Transactional
    public Iterable<AdmProcessDetail> findAll() {
        Iterable<AdmProcessDetail> admProcessDetails = admProcessDetailRepository.findAll();

        for (AdmProcessDetail admProcessDetail : admProcessDetails) {

        }

        return admProcessDetails;
    }

    @Transactional
    @Override
    public Iterable<AdmProcessDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmProcessDetail> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    public AdmProcessDetail delete(BigInteger id) throws AdmProcessDetailNotFoundException {

        AdmProcessDetail admProcessDetail = admProcessDetailRepository.findOne(id);

        if (admProcessDetail == null) {
            throw new AdmProcessDetailNotFoundException();
        }
        admProcessDetailRepository.delete(id);
        return admProcessDetail;
    }
}