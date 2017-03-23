package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPaymentNotFoundException;
import org.reflection.model.pos.PosPayment;
import org.reflection.repositories.PosPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posPaymentService")
@Transactional(readOnly = true)
public class PosPaymentServiceImpl implements PosPaymentService {

    @Autowired
    private PosPaymentRepository posPaymentRepository;

    @Transactional(readOnly = true)
    @Override
    public PosPayment findByCode(String code) throws PosPaymentNotFoundException {
        return posPaymentRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosPayment create(PosPayment lookup) {
        return posPaymentRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosPaymentNotFoundException.class)
    @Override
    public PosPayment update(PosPayment updated) throws PosPaymentNotFoundException {
        PosPayment posPayment = posPaymentRepository.findOne(updated.getId());
        if (posPayment == null) {
            throw new PosPaymentNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posPayment);

        posPayment.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posPayment.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posPayment);

        return posPaymentRepository.save(posPayment);
    }

    @Transactional(rollbackFor = PosPaymentNotFoundException.class)
    @Override
    public PosPayment copy(final PosPayment copied) {

        PosPayment posPaymentOrginal = posPaymentRepository.findOne(copied.getId());

        PosPayment posPayment = new PosPayment();
        //BeanUtils.copyProperties(copied, posPayment);
        //posPayment.setId(null);

        posPayment.getEmbdAuditable().setEntryBy(posPaymentOrginal.getEmbdAuditable().getEntryBy());
        posPayment.getEmbdAuditable().setEntryDate(posPaymentOrginal.getEmbdAuditable().getEntryDate());
        posPayment.getEmbdAuditable().setEditBy(posPaymentOrginal.getEmbdAuditable().getEditBy());
        posPayment.getEmbdAuditable().setEditDate(posPaymentOrginal.getEmbdAuditable().getEditDate());
        posPayment.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posPayment.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posPayment);


        return posPaymentRepository.save(posPayment);
    }

    private void copyProperties(PosPayment from, PosPayment to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosPurchaseMaster(from.getPosPurchaseMaster());
        to.setPosSupplier(from.getPosSupplier());
        to.setEmPosPaymentType(from.getEmPosPaymentType());

    }

    @Override
    @Transactional
    public PosPayment findById(BigInteger id) throws PosPaymentNotFoundException {
        PosPayment posPayment = posPaymentRepository.findOne(id);
        if (posPayment == null) {
            throw new PosPaymentNotFoundException();
        }

        return posPayment;
    }

    @Override
    @Transactional
    public Iterable<PosPayment> findAll() {
        Iterable<PosPayment> posPayments = posPaymentRepository.findAll();

        for (PosPayment posPayment : posPayments) {

        }

        return posPayments;
    }

    @Transactional
    @Override
    public Iterable<PosPayment> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosPayment> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosPaymentNotFoundException.class)
    public PosPayment delete(BigInteger id) throws PosPaymentNotFoundException {

        PosPayment posPayment = posPaymentRepository.findOne(id);

        if (posPayment == null) {
            throw new PosPaymentNotFoundException();
        }
        posPaymentRepository.delete(id);
        return posPayment;
    }
}