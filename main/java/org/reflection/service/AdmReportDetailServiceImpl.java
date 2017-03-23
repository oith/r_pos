package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportDetailNotFoundException;
import org.reflection.model.com.AdmReportDetail;
import org.reflection.repositories.AdmReportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admReportDetailService")
@Transactional(readOnly = true)
public class AdmReportDetailServiceImpl implements AdmReportDetailService {

    @Autowired
    private AdmReportDetailRepository admReportDetailRepository;


    @Transactional
    @Override
    public AdmReportDetail create(AdmReportDetail lookup) {
        return admReportDetailRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    @Override
    public AdmReportDetail update(AdmReportDetail updated) throws AdmReportDetailNotFoundException {
        AdmReportDetail admReportDetail = admReportDetailRepository.findOne(updated.getId());
        if (admReportDetail == null) {
            throw new AdmReportDetailNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admReportDetail);


        copyProperties(updated, admReportDetail);

        return admReportDetailRepository.save(admReportDetail);
    }

    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    @Override
    public AdmReportDetail copy(final AdmReportDetail copied) {

        AdmReportDetail admReportDetailOrginal = admReportDetailRepository.findOne(copied.getId());

        AdmReportDetail admReportDetail = new AdmReportDetail();
        //BeanUtils.copyProperties(copied, admReportDetail);
        //admReportDetail.setId(null);


        copyProperties(copied, admReportDetail);


        return admReportDetailRepository.save(admReportDetail);
    }

    private void copyProperties(AdmReportDetail from, AdmReportDetail to) {
        to.setSlNo(from.getSlNo());
        to.setAdmReport(from.getAdmReport());
        to.setAdmParam(from.getAdmParam());
        to.setIsActive(from.getIsActive());
        to.setIsMandatory(from.getIsMandatory());
        to.setDefaultVal(from.getDefaultVal());
        to.setHelpText(from.getHelpText());

    }

    @Override
    @Transactional
    public AdmReportDetail findById(BigInteger id) throws AdmReportDetailNotFoundException {
        AdmReportDetail admReportDetail = admReportDetailRepository.findOne(id);
        if (admReportDetail == null) {
            throw new AdmReportDetailNotFoundException();
        }

        return admReportDetail;
    }

    @Override
    @Transactional
    public Iterable<AdmReportDetail> findAll() {
        Iterable<AdmReportDetail> admReportDetails = admReportDetailRepository.findAll();

        for (AdmReportDetail admReportDetail : admReportDetails) {

        }

        return admReportDetails;
    }

    @Transactional
    @Override
    public Iterable<AdmReportDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmReportDetail> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    public AdmReportDetail delete(BigInteger id) throws AdmReportDetailNotFoundException {

        AdmReportDetail admReportDetail = admReportDetailRepository.findOne(id);

        if (admReportDetail == null) {
            throw new AdmReportDetailNotFoundException();
        }
        admReportDetailRepository.delete(id);
        return admReportDetail;
    }
}