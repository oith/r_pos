package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxLookupNotFoundException;
import org.reflection.model.sample.ZxLookup;
import org.reflection.repositories.ZxLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxLookupService")
@Transactional(readOnly = true)
public class ZxLookupServiceImpl implements ZxLookupService {

    @Autowired
    private ZxLookupRepository zxLookupRepository;


    @Transactional
    @Override
    public ZxLookup create(ZxLookup lookup) {
        return zxLookupRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup update(ZxLookup updated) throws ZxLookupNotFoundException {
        ZxLookup zxLookup = zxLookupRepository.findOne(updated.getId());
        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxLookup);

        zxLookup.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        zxLookup.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, zxLookup);

        return zxLookupRepository.save(zxLookup);
    }

    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup copy(final ZxLookup copied) {

        ZxLookup zxLookupOrginal = zxLookupRepository.findOne(copied.getId());

        ZxLookup zxLookup = new ZxLookup();
        //BeanUtils.copyProperties(copied, zxLookup);
        //zxLookup.setId(null);

        zxLookup.setCode(zxLookupOrginal.getCode());
        zxLookup.setCode(copied.getCode());
        zxLookup.getEmbdAuditable().setEntryBy(zxLookupOrginal.getEmbdAuditable().getEntryBy());
        zxLookup.getEmbdAuditable().setEntryDate(zxLookupOrginal.getEmbdAuditable().getEntryDate());
        zxLookup.getEmbdAuditable().setEditBy(zxLookupOrginal.getEmbdAuditable().getEditBy());
        zxLookup.getEmbdAuditable().setEditDate(zxLookupOrginal.getEmbdAuditable().getEditDate());
        zxLookup.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        zxLookup.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, zxLookup);


        return zxLookupRepository.save(zxLookup);
    }

    private void copyProperties(ZxLookup from, ZxLookup to) {
        to.setTitle(from.getTitle());
        to.setTitleNative(from.getTitleNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setRemarks(from.getRemarks());
        to.setZxLookupKeyword(from.getZxLookupKeyword());

    }

    @Override
    @Transactional
    public ZxLookup findById(BigInteger id) throws ZxLookupNotFoundException {
        ZxLookup zxLookup = zxLookupRepository.findOne(id);
        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }

        return zxLookup;
    }

    @Override
    @Transactional
    public Iterable<ZxLookup> findAll() {
        Iterable<ZxLookup> zxLookups = zxLookupRepository.findAll();

        for (ZxLookup zxLookup : zxLookups) {

        }

        return zxLookups;
    }

    @Transactional
    @Override
    public Iterable<ZxLookup> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxLookup> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    public ZxLookup delete(BigInteger id) throws ZxLookupNotFoundException {

        ZxLookup zxLookup = zxLookupRepository.findOne(id);

        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }
        zxLookupRepository.delete(id);
        return zxLookup;
    }
}