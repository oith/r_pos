package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessNotFoundException;
import org.reflection.model.com.AdmProcess;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.repositories.AdmProcessRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admProcessService")
@Transactional(readOnly = true)
public class AdmProcessServiceImpl implements AdmProcessService {

    @Autowired
    private AdmProcessRepository admProcessRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmProcess findByCode(String code) throws AdmProcessNotFoundException {
        return admProcessRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmProcess create(AdmProcess lookup) {
        return admProcessRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    @Override
    public AdmProcess update(AdmProcess updated) throws AdmProcessNotFoundException {
        AdmProcess admProcess = admProcessRepository.findOne(updated.getId());
        if (admProcess == null) {
            throw new AdmProcessNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admProcess);


        copyProperties(updated, admProcess);

        return admProcessRepository.save(admProcess);
    }

    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    @Override
    public AdmProcess copy(final AdmProcess copied) {

        AdmProcess admProcessOrginal = admProcessRepository.findOne(copied.getId());

        AdmProcess admProcess = new AdmProcess();
        //BeanUtils.copyProperties(copied, admProcess);
        //admProcess.setId(null);


        copyProperties(copied, admProcess);

        for (AdmProcessDetail currDet : admProcessOrginal.getAdmProcessDetails()) {

            AdmProcessDetail det = new AdmProcessDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmProcess(admProcess);
            if (admProcess.getAdmProcessDetails() == null) {
                admProcess.setAdmProcessDetails(new java.util.LinkedHashSet());
            }
            admProcess.getAdmProcessDetails().add(det);
        }


        return admProcessRepository.save(admProcess);
    }

    private void copyProperties(AdmProcess from, AdmProcess to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());
        to.setAdmModule(from.getAdmModule());
        to.setCmd(from.getCmd());
        to.setQuery(from.getQuery());
        to.setQueryAlias(from.getQueryAlias());
        to.setAdmProcessDetails(from.getAdmProcessDetails());
        to.setProcessBtns(from.getProcessBtns());

    }

    @Override
    @Transactional
    public AdmProcess findById(BigInteger id) throws AdmProcessNotFoundException {
        AdmProcess admProcess = admProcessRepository.findOne(id);
        if (admProcess == null) {
            throw new AdmProcessNotFoundException();
        }
        Hibernate.initialize(admProcess.getAdmProcessDetails());

        return admProcess;
    }

    @Override
    @Transactional
    public Iterable<AdmProcess> findAll() {
        Iterable<AdmProcess> admProcesss = admProcessRepository.findAll();

        for (AdmProcess admProcess : admProcesss) {
            Hibernate.initialize(admProcess.getAdmProcessDetails());

        }

        return admProcesss;
    }

    @Transactional
    @Override
    public Iterable<AdmProcess> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmProcess> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmProcessNotFoundException.class)
    public AdmProcess delete(BigInteger id) throws AdmProcessNotFoundException {

        AdmProcess admProcess = admProcessRepository.findOne(id);

        if (admProcess == null) {
            throw new AdmProcessNotFoundException();
        }
        admProcessRepository.delete(id);
        return admProcess;
    }
}