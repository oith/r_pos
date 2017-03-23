package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmSubModuleNotFoundException;
import org.reflection.model.com.AdmMenuCommon;
import org.reflection.model.com.AdmProcess;
import org.reflection.model.com.AdmReport;
import org.reflection.model.com.AdmSubModule;
import org.reflection.repositories.AdmSubModuleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admSubModuleService")
@Transactional(readOnly = true)
public class AdmSubModuleServiceImpl implements AdmSubModuleService {

    @Autowired
    private AdmSubModuleRepository admSubModuleRepository;


    @Transactional
    @Override
    public AdmSubModule create(AdmSubModule lookup) {
        return admSubModuleRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmSubModuleNotFoundException.class)
    @Override
    public AdmSubModule update(AdmSubModule updated) throws AdmSubModuleNotFoundException {
        AdmSubModule admSubModule = admSubModuleRepository.findOne(updated.getId());
        if (admSubModule == null) {
            throw new AdmSubModuleNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admSubModule);


        copyProperties(updated, admSubModule);

        return admSubModuleRepository.save(admSubModule);
    }

    @Transactional(rollbackFor = AdmSubModuleNotFoundException.class)
    @Override
    public AdmSubModule copy(final AdmSubModule copied) {

        AdmSubModule admSubModuleOrginal = admSubModuleRepository.findOne(copied.getId());

        AdmSubModule admSubModule = new AdmSubModule();
        //BeanUtils.copyProperties(copied, admSubModule);
        //admSubModule.setId(null);


        copyProperties(copied, admSubModule);

        for (AdmMenuCommon currDet : admSubModuleOrginal.getAdmMenuCommons()) {

            AdmMenuCommon det = new AdmMenuCommon();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmSubModule(admSubModule);
            if (admSubModule.getAdmMenuCommons() == null) {
                admSubModule.setAdmMenuCommons(new java.util.LinkedHashSet());
            }
            admSubModule.getAdmMenuCommons().add(det);
        }
        for (AdmProcess currDet : admSubModuleOrginal.getAdmProcesss()) {

            AdmProcess det = new AdmProcess();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmSubModule(admSubModule);
            if (admSubModule.getAdmProcesss() == null) {
                admSubModule.setAdmProcesss(new java.util.LinkedHashSet());
            }
            admSubModule.getAdmProcesss().add(det);
        }
        for (AdmReport currDet : admSubModuleOrginal.getAdmReports()) {

            AdmReport det = new AdmReport();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmSubModule(admSubModule);
            if (admSubModule.getAdmReports() == null) {
                admSubModule.setAdmReports(new java.util.LinkedHashSet());
            }
            admSubModule.getAdmReports().add(det);
        }


        return admSubModuleRepository.save(admSubModule);
    }

    private void copyProperties(AdmSubModule from, AdmSubModule to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());
        to.setAdmModule(from.getAdmModule());
        to.setDisplayIconClass(from.getDisplayIconClass());
        to.setAdmMenuCommons(from.getAdmMenuCommons());
        to.setAdmProcesss(from.getAdmProcesss());
        to.setAdmReports(from.getAdmReports());

    }

    @Override
    @Transactional
    public AdmSubModule findById(BigInteger id) throws AdmSubModuleNotFoundException {
        AdmSubModule admSubModule = admSubModuleRepository.findOne(id);
        if (admSubModule == null) {
            throw new AdmSubModuleNotFoundException();
        }
        Hibernate.initialize(admSubModule.getAdmMenuCommons());
        Hibernate.initialize(admSubModule.getAdmProcesss());
        Hibernate.initialize(admSubModule.getAdmReports());

        return admSubModule;
    }

    @Override
    @Transactional
    public Iterable<AdmSubModule> findAll() {
        Iterable<AdmSubModule> admSubModules = admSubModuleRepository.findAll();

        for (AdmSubModule admSubModule : admSubModules) {
            Hibernate.initialize(admSubModule.getAdmMenuCommons());
            Hibernate.initialize(admSubModule.getAdmProcesss());
            Hibernate.initialize(admSubModule.getAdmReports());

        }

        return admSubModules;
    }

    @Transactional
    @Override
    public Iterable<AdmSubModule> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmSubModule> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmSubModuleNotFoundException.class)
    public AdmSubModule delete(BigInteger id) throws AdmSubModuleNotFoundException {

        AdmSubModule admSubModule = admSubModuleRepository.findOne(id);

        if (admSubModule == null) {
            throw new AdmSubModuleNotFoundException();
        }
        admSubModuleRepository.delete(id);
        return admSubModule;
    }
}