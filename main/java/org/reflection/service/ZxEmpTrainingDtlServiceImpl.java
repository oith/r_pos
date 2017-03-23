package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpTrainingDtlNotFoundException;
import org.reflection.model.sample.ZxEmpTrainingDtl;
import org.reflection.repositories.ZxEmpTrainingDtlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxEmpTrainingDtlService")
@Transactional(readOnly = true)
public class ZxEmpTrainingDtlServiceImpl implements ZxEmpTrainingDtlService {

    @Autowired
    private ZxEmpTrainingDtlRepository zxEmpTrainingDtlRepository;


    @Transactional
    @Override
    public ZxEmpTrainingDtl create(ZxEmpTrainingDtl lookup) {
        return zxEmpTrainingDtlRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxEmpTrainingDtlNotFoundException.class)
    @Override
    public ZxEmpTrainingDtl update(ZxEmpTrainingDtl updated) throws ZxEmpTrainingDtlNotFoundException {
        ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlRepository.findOne(updated.getId());
        if (zxEmpTrainingDtl == null) {
            throw new ZxEmpTrainingDtlNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxEmpTrainingDtl);


        copyProperties(updated, zxEmpTrainingDtl);

        return zxEmpTrainingDtlRepository.save(zxEmpTrainingDtl);
    }

    @Transactional(rollbackFor = ZxEmpTrainingDtlNotFoundException.class)
    @Override
    public ZxEmpTrainingDtl copy(final ZxEmpTrainingDtl copied) {

        ZxEmpTrainingDtl zxEmpTrainingDtlOrginal = zxEmpTrainingDtlRepository.findOne(copied.getId());

        ZxEmpTrainingDtl zxEmpTrainingDtl = new ZxEmpTrainingDtl();
        //BeanUtils.copyProperties(copied, zxEmpTrainingDtl);
        //zxEmpTrainingDtl.setId(null);


        copyProperties(copied, zxEmpTrainingDtl);


        return zxEmpTrainingDtlRepository.save(zxEmpTrainingDtl);
    }

    private void copyProperties(ZxEmpTrainingDtl from, ZxEmpTrainingDtl to) {
        to.setZxEmp(from.getZxEmp());
        to.setZxLookupTraining(from.getZxLookupTraining());
        to.setTrainingOrg(from.getTrainingOrg());
        to.setFromDate(from.getFromDate());
        to.setToDate(from.getToDate());
        to.setRemarks(from.getRemarks());
        to.setSlNo(from.getSlNo());
        to.setCertificate(from.getCertificate());
        to.setPic(from.getPic());
        to.setZxEmpWhoCheckedBy(from.getZxEmpWhoCheckedBy());

    }

    @Override
    @Transactional
    public ZxEmpTrainingDtl findById(BigInteger id) throws ZxEmpTrainingDtlNotFoundException {
        ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlRepository.findOne(id);
        if (zxEmpTrainingDtl == null) {
            throw new ZxEmpTrainingDtlNotFoundException();
        }

        return zxEmpTrainingDtl;
    }

    @Override
    @Transactional
    public Iterable<ZxEmpTrainingDtl> findAll() {
        Iterable<ZxEmpTrainingDtl> zxEmpTrainingDtls = zxEmpTrainingDtlRepository.findAll();

        for (ZxEmpTrainingDtl zxEmpTrainingDtl : zxEmpTrainingDtls) {

        }

        return zxEmpTrainingDtls;
    }

    @Transactional
    @Override
    public Iterable<ZxEmpTrainingDtl> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxEmpTrainingDtl> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxEmpTrainingDtlNotFoundException.class)
    public ZxEmpTrainingDtl delete(BigInteger id) throws ZxEmpTrainingDtlNotFoundException {

        ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlRepository.findOne(id);

        if (zxEmpTrainingDtl == null) {
            throw new ZxEmpTrainingDtlNotFoundException();
        }
        zxEmpTrainingDtlRepository.delete(id);
        return zxEmpTrainingDtl;
    }
}