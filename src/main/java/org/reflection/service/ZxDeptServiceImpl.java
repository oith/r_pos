package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxDeptNotFoundException;
import org.reflection.model.sample.ZxDept;
import org.reflection.repositories.ZxDeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxDeptService")
@Transactional(readOnly = true)
public class ZxDeptServiceImpl implements ZxDeptService {

    @Autowired
    private ZxDeptRepository zxDeptRepository;


    @Transactional
    @Override
    public ZxDept create(ZxDept lookup) {
        return zxDeptRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    @Override
    public ZxDept update(ZxDept updated) throws ZxDeptNotFoundException {
        ZxDept zxDept = zxDeptRepository.findOne(updated.getId());
        if (zxDept == null) {
            throw new ZxDeptNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxDept);


        copyProperties(updated, zxDept);

        return zxDeptRepository.save(zxDept);
    }

    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    @Override
    public ZxDept copy(final ZxDept copied) {

        ZxDept zxDeptOrginal = zxDeptRepository.findOne(copied.getId());

        ZxDept zxDept = new ZxDept();
        //BeanUtils.copyProperties(copied, zxDept);
        //zxDept.setId(null);


        copyProperties(copied, zxDept);


        return zxDeptRepository.save(zxDept);
    }

    private void copyProperties(ZxDept from, ZxDept to) {
        to.setCode(from.getCode());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setTitle(from.getTitle());
        to.setTitleNative(from.getTitleNative());
        to.setRemarks(from.getRemarks());

    }

    @Override
    @Transactional
    public ZxDept findById(BigInteger id) throws ZxDeptNotFoundException {
        ZxDept zxDept = zxDeptRepository.findOne(id);
        if (zxDept == null) {
            throw new ZxDeptNotFoundException();
        }

        return zxDept;
    }

    @Override
    @Transactional
    public Iterable<ZxDept> findAll() {
        Iterable<ZxDept> zxDepts = zxDeptRepository.findAll();

        for (ZxDept zxDept : zxDepts) {

        }

        return zxDepts;
    }

    @Transactional
    @Override
    public Iterable<ZxDept> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxDept> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxDeptNotFoundException.class)
    public ZxDept delete(BigInteger id) throws ZxDeptNotFoundException {

        ZxDept zxDept = zxDeptRepository.findOne(id);

        if (zxDept == null) {
            throw new ZxDeptNotFoundException();
        }
        zxDeptRepository.delete(id);
        return zxDept;
    }
}