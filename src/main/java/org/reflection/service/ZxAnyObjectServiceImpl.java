package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxAnyObjectNotFoundException;
import org.reflection.model.sample.ZxAnyObject;
import org.reflection.repositories.ZxAnyObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxAnyObjectService")
@Transactional(readOnly = true)
public class ZxAnyObjectServiceImpl implements ZxAnyObjectService {

    @Autowired
    private ZxAnyObjectRepository zxAnyObjectRepository;


    @Transactional
    @Override
    public ZxAnyObject create(ZxAnyObject lookup) {
        return zxAnyObjectRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    @Override
    public ZxAnyObject update(ZxAnyObject updated) throws ZxAnyObjectNotFoundException {
        ZxAnyObject zxAnyObject = zxAnyObjectRepository.findOne(updated.getId());
        if (zxAnyObject == null) {
            throw new ZxAnyObjectNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxAnyObject);


        copyProperties(updated, zxAnyObject);

        return zxAnyObjectRepository.save(zxAnyObject);
    }

    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    @Override
    public ZxAnyObject copy(final ZxAnyObject copied) {

        ZxAnyObject zxAnyObjectOrginal = zxAnyObjectRepository.findOne(copied.getId());

        ZxAnyObject zxAnyObject = new ZxAnyObject();
        //BeanUtils.copyProperties(copied, zxAnyObject);
        //zxAnyObject.setId(null);


        copyProperties(copied, zxAnyObject);


        return zxAnyObjectRepository.save(zxAnyObject);
    }

    private void copyProperties(ZxAnyObject from, ZxAnyObject to) {
        to.setCode(from.getCode());
        to.setTitle(from.getTitle());
        to.setRemarks(from.getRemarks());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());

    }

    @Override
    @Transactional
    public ZxAnyObject findById(BigInteger id) throws ZxAnyObjectNotFoundException {
        ZxAnyObject zxAnyObject = zxAnyObjectRepository.findOne(id);
        if (zxAnyObject == null) {
            throw new ZxAnyObjectNotFoundException();
        }

        return zxAnyObject;
    }

    @Override
    @Transactional
    public Iterable<ZxAnyObject> findAll() {
        Iterable<ZxAnyObject> zxAnyObjects = zxAnyObjectRepository.findAll();

        for (ZxAnyObject zxAnyObject : zxAnyObjects) {

        }

        return zxAnyObjects;
    }

    @Transactional
    @Override
    public Iterable<ZxAnyObject> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxAnyObject> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    public ZxAnyObject delete(BigInteger id) throws ZxAnyObjectNotFoundException {

        ZxAnyObject zxAnyObject = zxAnyObjectRepository.findOne(id);

        if (zxAnyObject == null) {
            throw new ZxAnyObjectNotFoundException();
        }
        zxAnyObjectRepository.delete(id);
        return zxAnyObject;
    }
}