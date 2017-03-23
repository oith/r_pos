package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.model.com.AdmReport;
import org.reflection.model.com.AdmReportDetail;
import org.reflection.repositories.AdmReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admReportService")
@Transactional(readOnly = true)
public class AdmReportServiceImpl implements AdmReportService {

    @Autowired
    private AdmReportRepository admReportRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmReport findByCode(String code) throws AdmReportNotFoundException {
        return admReportRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmReport create(AdmReport lookup) {
        return admReportRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    @Override
    public AdmReport update(AdmReport updated) throws AdmReportNotFoundException {
        AdmReport admReport = admReportRepository.findOne(updated.getId());
        if (admReport == null) {
            throw new AdmReportNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admReport);


        copyProperties(updated, admReport);

        return admReportRepository.save(admReport);
    }

    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    @Override
    public AdmReport copy(final AdmReport copied) {

        AdmReport admReportOrginal = admReportRepository.findOne(copied.getId());

        AdmReport admReport = new AdmReport();
        //BeanUtils.copyProperties(copied, admReport);
        //admReport.setId(null);


        copyProperties(copied, admReport);

        for (AdmReportDetail currDet : admReportOrginal.getAdmReportDetails()) {

            AdmReportDetail det = new AdmReportDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmReport(admReport);
            if (admReport.getAdmReportDetails() == null) {
                admReport.setAdmReportDetails(new java.util.LinkedHashSet());
            }
            admReport.getAdmReportDetails().add(det);
        }


        return admReportRepository.save(admReport);
    }

    private void copyProperties(AdmReport from, AdmReport to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());
        to.setAdmSubModule(from.getAdmSubModule());
        to.setFileName(from.getFileName());
        to.setAdmReportDetails(from.getAdmReportDetails());
        to.setSupportFormats(from.getSupportFormats());

    }

    @Override
    @Transactional
    public AdmReport findById(BigInteger id) throws AdmReportNotFoundException {
        AdmReport admReport = admReportRepository.findOne(id);
        if (admReport == null) {
            throw new AdmReportNotFoundException();
        }
        Hibernate.initialize(admReport.getAdmReportDetails());
        Hibernate.initialize(admReport.getSupportFormats());

        return admReport;
    }

    @Override
    @Transactional
    public Iterable<AdmReport> findAll() {
        Iterable<AdmReport> admReports = admReportRepository.findAll();

        for (AdmReport admReport : admReports) {
            Hibernate.initialize(admReport.getAdmReportDetails());
            Hibernate.initialize(admReport.getSupportFormats());

        }

        return admReports;
    }

    @Transactional
    @Override
    public Iterable<AdmReport> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmReport> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmReportNotFoundException.class)
    public AdmReport delete(BigInteger id) throws AdmReportNotFoundException {

        AdmReport admReport = admReportRepository.findOne(id);

        if (admReport == null) {
            throw new AdmReportNotFoundException();
        }
        admReportRepository.delete(id);
        return admReport;
    }
}