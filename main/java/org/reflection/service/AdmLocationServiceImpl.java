package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmLocationNotFoundException;
import org.reflection.model.com.AdmLocation;
import org.reflection.repositories.AdmLocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admLocationService")
@Transactional(readOnly = true)
public class AdmLocationServiceImpl implements AdmLocationService {

    @Autowired
    private AdmLocationRepository admLocationRepository;


    @Transactional
    @Override
    public AdmLocation create(AdmLocation lookup) {
        return admLocationRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmLocationNotFoundException.class)
    @Override
    public AdmLocation update(AdmLocation updated) throws AdmLocationNotFoundException {
        AdmLocation admLocation = admLocationRepository.findOne(updated.getId());
        if (admLocation == null) {
            throw new AdmLocationNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admLocation);


        copyProperties(updated, admLocation);

        return admLocationRepository.save(admLocation);
    }

    @Transactional(rollbackFor = AdmLocationNotFoundException.class)
    @Override
    public AdmLocation copy(final AdmLocation copied) {

        AdmLocation admLocationOrginal = admLocationRepository.findOne(copied.getId());

        AdmLocation admLocation = new AdmLocation();
        //BeanUtils.copyProperties(copied, admLocation);
        //admLocation.setId(null);


        copyProperties(copied, admLocation);

        for (AdmLocation currDet : admLocationOrginal.getAdmLocations()) {

            AdmLocation det = new AdmLocation();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setParentAdmLocation(admLocation);
            if (admLocation.getAdmLocations() == null) {
                admLocation.setAdmLocations(new java.util.LinkedHashSet());
            }
            admLocation.getAdmLocations().add(det);
        }


        return admLocationRepository.save(admLocation);
    }

    private void copyProperties(AdmLocation from, AdmLocation to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setEmLocationType(from.getEmLocationType());
        to.setAdmLocations(from.getAdmLocations());
        to.setParentAdmLocation(from.getParentAdmLocation());

    }

    @Override
    @Transactional
    public AdmLocation findById(BigInteger id) throws AdmLocationNotFoundException {
        AdmLocation admLocation = admLocationRepository.findOne(id);
        if (admLocation == null) {
            throw new AdmLocationNotFoundException();
        }
        Hibernate.initialize(admLocation.getAdmLocations());

        return admLocation;
    }

    @Override
    @Transactional
    public Iterable<AdmLocation> findAll() {
        Iterable<AdmLocation> admLocations = admLocationRepository.findAll();

        for (AdmLocation admLocation : admLocations) {
            Hibernate.initialize(admLocation.getAdmLocations());

        }

        return admLocations;
    }

    @Transactional
    @Override
    public Iterable<AdmLocation> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmLocation> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmLocationNotFoundException.class)
    public AdmLocation delete(BigInteger id) throws AdmLocationNotFoundException {

        AdmLocation admLocation = admLocationRepository.findOne(id);

        if (admLocation == null) {
            throw new AdmLocationNotFoundException();
        }
        admLocationRepository.delete(id);
        return admLocation;
    }
}