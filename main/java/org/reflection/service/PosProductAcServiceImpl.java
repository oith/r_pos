package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosProductAcNotFoundException;
import org.reflection.model.pos.PosProductAc;
import org.reflection.repositories.PosProductAcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posProductAcService")
@Transactional(readOnly = true)
public class PosProductAcServiceImpl implements PosProductAcService {

    @Autowired
    private PosProductAcRepository posProductAcRepository;


    @Transactional
    @Override
    public PosProductAc create(PosProductAc lookup) {
        return posProductAcRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosProductAcNotFoundException.class)
    @Override
    public PosProductAc update(PosProductAc updated) throws PosProductAcNotFoundException {
        PosProductAc posProductAc = posProductAcRepository.findOne(updated.getId());
        if (posProductAc == null) {
            throw new PosProductAcNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posProductAc);


        copyProperties(updated, posProductAc);

        return posProductAcRepository.save(posProductAc);
    }

    @Transactional(rollbackFor = PosProductAcNotFoundException.class)
    @Override
    public PosProductAc copy(final PosProductAc copied) {

        PosProductAc posProductAcOrginal = posProductAcRepository.findOne(copied.getId());

        PosProductAc posProductAc = new PosProductAc();
        //BeanUtils.copyProperties(copied, posProductAc);
        //posProductAc.setId(null);


        copyProperties(copied, posProductAc);


        return posProductAcRepository.save(posProductAc);
    }

    private void copyProperties(PosProductAc from, PosProductAc to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setEmPosAnalysisCode(from.getEmPosAnalysisCode());

    }

    @Override
    @Transactional
    public PosProductAc findById(BigInteger id) throws PosProductAcNotFoundException {
        PosProductAc posProductAc = posProductAcRepository.findOne(id);
        if (posProductAc == null) {
            throw new PosProductAcNotFoundException();
        }

        return posProductAc;
    }

    @Override
    @Transactional
    public Iterable<PosProductAc> findAll() {
        Iterable<PosProductAc> posProductAcs = posProductAcRepository.findAll();

        for (PosProductAc posProductAc : posProductAcs) {

        }

        return posProductAcs;
    }

    @Transactional
    @Override
    public Iterable<PosProductAc> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosProductAc> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosProductAcNotFoundException.class)
    public PosProductAc delete(BigInteger id) throws PosProductAcNotFoundException {

        PosProductAc posProductAc = posProductAcRepository.findOne(id);

        if (posProductAc == null) {
            throw new PosProductAcNotFoundException();
        }
        posProductAcRepository.delete(id);
        return posProductAc;
    }
}