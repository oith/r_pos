package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSupplierNotFoundException;
import org.reflection.model.pos.PosSupplier;
import org.reflection.repositories.PosSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posSupplierService")
@Transactional(readOnly = true)
public class PosSupplierServiceImpl implements PosSupplierService {

    @Autowired
    private PosSupplierRepository posSupplierRepository;

    @Transactional(readOnly = true)
    @Override
    public PosSupplier findByMobile(String mobile) throws PosSupplierNotFoundException {
        return posSupplierRepository.findByMobile(mobile);
    }

    @Transactional
    @Override
    public PosSupplier create(PosSupplier lookup) {
        return posSupplierRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosSupplierNotFoundException.class)
    @Override
    public PosSupplier update(PosSupplier updated) throws PosSupplierNotFoundException {
        PosSupplier posSupplier = posSupplierRepository.findOne(updated.getId());
        if (posSupplier == null) {
            throw new PosSupplierNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posSupplier);


        copyProperties(updated, posSupplier);

        return posSupplierRepository.save(posSupplier);
    }

    @Transactional(rollbackFor = PosSupplierNotFoundException.class)
    @Override
    public PosSupplier copy(final PosSupplier copied) {

        PosSupplier posSupplierOrginal = posSupplierRepository.findOne(copied.getId());

        PosSupplier posSupplier = new PosSupplier();
        //BeanUtils.copyProperties(copied, posSupplier);
        //posSupplier.setId(null);


        copyProperties(copied, posSupplier);


        return posSupplierRepository.save(posSupplier);
    }

    private void copyProperties(PosSupplier from, PosSupplier to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setEmPosAnalysisCode(from.getEmPosAnalysisCode());
        to.setMobile(from.getMobile());
        to.setAddress(from.getAddress());
        to.setEmPosSupplierGroup(from.getEmPosSupplierGroup());

    }

    @Override
    @Transactional
    public PosSupplier findById(BigInteger id) throws PosSupplierNotFoundException {
        PosSupplier posSupplier = posSupplierRepository.findOne(id);
        if (posSupplier == null) {
            throw new PosSupplierNotFoundException();
        }

        return posSupplier;
    }

    @Override
    @Transactional
    public Iterable<PosSupplier> findAll() {
        Iterable<PosSupplier> posSuppliers = posSupplierRepository.findAll();

        for (PosSupplier posSupplier : posSuppliers) {

        }

        return posSuppliers;
    }

    @Transactional
    @Override
    public Iterable<PosSupplier> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosSupplier> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosSupplierNotFoundException.class)
    public PosSupplier delete(BigInteger id) throws PosSupplierNotFoundException {

        PosSupplier posSupplier = posSupplierRepository.findOne(id);

        if (posSupplier == null) {
            throw new PosSupplierNotFoundException();
        }
        posSupplierRepository.delete(id);
        return posSupplier;
    }
}