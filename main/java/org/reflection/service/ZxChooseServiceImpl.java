package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxChooseNotFoundException;
import org.reflection.model.sample.ZxChoose;
import org.reflection.repositories.ZxChooseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxChooseService")
@Transactional(readOnly = true)
public class ZxChooseServiceImpl implements ZxChooseService {

    @Autowired
    private ZxChooseRepository zxChooseRepository;


    @Transactional
    @Override
    public ZxChoose create(ZxChoose lookup) {
        return zxChooseRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxChooseNotFoundException.class)
    @Override
    public ZxChoose update(ZxChoose updated) throws ZxChooseNotFoundException {
        ZxChoose zxChoose = zxChooseRepository.findOne(updated.getId());
        if (zxChoose == null) {
            throw new ZxChooseNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxChoose);


        copyProperties(updated, zxChoose);

        return zxChooseRepository.save(zxChoose);
    }

    @Transactional(rollbackFor = ZxChooseNotFoundException.class)
    @Override
    public ZxChoose copy(final ZxChoose copied) {

        ZxChoose zxChooseOrginal = zxChooseRepository.findOne(copied.getId());

        ZxChoose zxChoose = new ZxChoose();
        //BeanUtils.copyProperties(copied, zxChoose);
        //zxChoose.setId(null);


        copyProperties(copied, zxChoose);


        return zxChooseRepository.save(zxChoose);
    }

    private void copyProperties(ZxChoose from, ZxChoose to) {
        to.setChoose(from.getChoose());
        to.setIsActive(from.getIsActive());

    }

    @Override
    @Transactional
    public ZxChoose findById(BigInteger id) throws ZxChooseNotFoundException {
        ZxChoose zxChoose = zxChooseRepository.findOne(id);
        if (zxChoose == null) {
            throw new ZxChooseNotFoundException();
        }

        return zxChoose;
    }

    @Override
    @Transactional
    public Iterable<ZxChoose> findAll() {
        Iterable<ZxChoose> zxChooses = zxChooseRepository.findAll();

        for (ZxChoose zxChoose : zxChooses) {

        }

        return zxChooses;
    }

    @Transactional
    @Override
    public Iterable<ZxChoose> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxChoose> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxChooseNotFoundException.class)
    public ZxChoose delete(BigInteger id) throws ZxChooseNotFoundException {

        ZxChoose zxChoose = zxChooseRepository.findOne(id);

        if (zxChoose == null) {
            throw new ZxChooseNotFoundException();
        }
        zxChooseRepository.delete(id);
        return zxChoose;
    }
}