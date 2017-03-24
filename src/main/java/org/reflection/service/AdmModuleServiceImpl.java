package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmModuleNotFoundException;
import org.reflection.model.com.AdmModule;
import org.reflection.repositories.AdmModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admModuleService")
@Transactional(readOnly = true)
public class AdmModuleServiceImpl implements AdmModuleService {

    @Autowired
    private AdmModuleRepository admModuleRepository;


    @Transactional
    @Override
    public AdmModule create(AdmModule lookup) {
        return admModuleRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    @Override
    public AdmModule update(AdmModule updated) throws AdmModuleNotFoundException {
        AdmModule admModule = admModuleRepository.findOne(updated.getId());
        if (admModule == null) {
            throw new AdmModuleNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admModule);


        copyProperties(updated, admModule);

        return admModuleRepository.save(admModule);
    }

    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    @Override
    public AdmModule copy(final AdmModule copied) {

        AdmModule admModuleOrginal = admModuleRepository.findOne(copied.getId());

        AdmModule admModule = new AdmModule();
        //BeanUtils.copyProperties(copied, admModule);
        //admModule.setId(null);


        copyProperties(copied, admModule);




        return admModuleRepository.save(admModule);
    }

    private void copyProperties(AdmModule from, AdmModule to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());

    }

    @Override
    @Transactional
    public AdmModule findById(BigInteger id) throws AdmModuleNotFoundException {
        AdmModule admModule = admModuleRepository.findOne(id);
        if (admModule == null) {
            throw new AdmModuleNotFoundException();
        }

        return admModule;
    }

    @Override
    @Transactional
    public Iterable<AdmModule> findAll() {
        Iterable<AdmModule> admModules = admModuleRepository.findAll();

        return admModules;
    }

    @Transactional
    @Override
    public Iterable<AdmModule> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmModule> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmModuleNotFoundException.class)
    public AdmModule delete(BigInteger id) throws AdmModuleNotFoundException {

        AdmModule admModule = admModuleRepository.findOne(id);

        if (admModule == null) {
            throw new AdmModuleNotFoundException();
        }
        admModuleRepository.delete(id);
        return admModule;
    }
}