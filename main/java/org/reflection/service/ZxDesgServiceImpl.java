package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxDesgNotFoundException;
import org.reflection.model.sample.ZxDesg;
import org.reflection.repositories.ZxDesgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxDesgService")
@Transactional(readOnly = true)
public class ZxDesgServiceImpl implements ZxDesgService {

    @Autowired
    private ZxDesgRepository zxDesgRepository;


    @Transactional
    @Override
    public ZxDesg create(ZxDesg lookup) {
        return zxDesgRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    @Override
    public ZxDesg update(ZxDesg updated) throws ZxDesgNotFoundException {
        ZxDesg zxDesg = zxDesgRepository.findOne(updated.getId());
        if (zxDesg == null) {
            throw new ZxDesgNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxDesg);


        copyProperties(updated, zxDesg);

        return zxDesgRepository.save(zxDesg);
    }

    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    @Override
    public ZxDesg copy(final ZxDesg copied) {

        ZxDesg zxDesgOrginal = zxDesgRepository.findOne(copied.getId());

        ZxDesg zxDesg = new ZxDesg();
        //BeanUtils.copyProperties(copied, zxDesg);
        //zxDesg.setId(null);


        copyProperties(copied, zxDesg);


        return zxDesgRepository.save(zxDesg);
    }

    private void copyProperties(ZxDesg from, ZxDesg to) {
        to.setCode(from.getCode());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setTitle(from.getTitle());
        to.setTitleNative(from.getTitleNative());
        to.setRemarks(from.getRemarks());

    }

    @Override
    @Transactional
    public ZxDesg findById(BigInteger id) throws ZxDesgNotFoundException {
        ZxDesg zxDesg = zxDesgRepository.findOne(id);
        if (zxDesg == null) {
            throw new ZxDesgNotFoundException();
        }

        return zxDesg;
    }

    @Override
    @Transactional
    public Iterable<ZxDesg> findAll() {
        Iterable<ZxDesg> zxDesgs = zxDesgRepository.findAll();

        for (ZxDesg zxDesg : zxDesgs) {

        }

        return zxDesgs;
    }

    @Transactional
    @Override
    public Iterable<ZxDesg> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxDesg> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    public ZxDesg delete(BigInteger id) throws ZxDesgNotFoundException {

        ZxDesg zxDesg = zxDesgRepository.findOne(id);

        if (zxDesg == null) {
            throw new ZxDesgNotFoundException();
        }
        zxDesgRepository.delete(id);
        return zxDesg;
    }
}