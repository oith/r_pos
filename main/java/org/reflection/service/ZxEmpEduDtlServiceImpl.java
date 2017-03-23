package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpEduDtlNotFoundException;
import org.reflection.model.sample.ZxEmpEduDtl;
import org.reflection.repositories.ZxEmpEduDtlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxEmpEduDtlService")
@Transactional(readOnly = true)
public class ZxEmpEduDtlServiceImpl implements ZxEmpEduDtlService {

    @Autowired
    private ZxEmpEduDtlRepository zxEmpEduDtlRepository;


    @Transactional
    @Override
    public ZxEmpEduDtl create(ZxEmpEduDtl lookup) {
        return zxEmpEduDtlRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl update(ZxEmpEduDtl updated) throws ZxEmpEduDtlNotFoundException {
        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlRepository.findOne(updated.getId());
        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxEmpEduDtl);


        copyProperties(updated, zxEmpEduDtl);

        return zxEmpEduDtlRepository.save(zxEmpEduDtl);
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl copy(final ZxEmpEduDtl copied) {

        ZxEmpEduDtl zxEmpEduDtlOrginal = zxEmpEduDtlRepository.findOne(copied.getId());

        ZxEmpEduDtl zxEmpEduDtl = new ZxEmpEduDtl();
        //BeanUtils.copyProperties(copied, zxEmpEduDtl);
        //zxEmpEduDtl.setId(null);


        copyProperties(copied, zxEmpEduDtl);


        return zxEmpEduDtlRepository.save(zxEmpEduDtl);
    }

    private void copyProperties(ZxEmpEduDtl from, ZxEmpEduDtl to) {
        to.setZxEmp(from.getZxEmp());
        to.setZxLookupEduLvl(from.getZxLookupEduLvl());
        to.setEduOrg(from.getEduOrg());
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
    public ZxEmpEduDtl findById(BigInteger id) throws ZxEmpEduDtlNotFoundException {
        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlRepository.findOne(id);
        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }

        return zxEmpEduDtl;
    }

    @Override
    @Transactional
    public Iterable<ZxEmpEduDtl> findAll() {
        Iterable<ZxEmpEduDtl> zxEmpEduDtls = zxEmpEduDtlRepository.findAll();

        for (ZxEmpEduDtl zxEmpEduDtl : zxEmpEduDtls) {

        }

        return zxEmpEduDtls;
    }

    @Transactional
    @Override
    public Iterable<ZxEmpEduDtl> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxEmpEduDtl> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    public ZxEmpEduDtl delete(BigInteger id) throws ZxEmpEduDtlNotFoundException {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlRepository.findOne(id);

        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }
        zxEmpEduDtlRepository.delete(id);
        return zxEmpEduDtl;
    }
}