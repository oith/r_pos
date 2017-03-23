package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseMasterNotFoundException;
import org.reflection.model.pos.PosPurchaseDetail;
import org.reflection.model.pos.PosPurchaseMaster;
import org.reflection.repositories.PosPurchaseMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posPurchaseMasterService")
@Transactional(readOnly = true)
public class PosPurchaseMasterServiceImpl implements PosPurchaseMasterService {

    @Autowired
    private PosPurchaseMasterRepository posPurchaseMasterRepository;

    @Transactional(readOnly = true)
    @Override
    public PosPurchaseMaster findByCode(String code) throws PosPurchaseMasterNotFoundException {
        return posPurchaseMasterRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosPurchaseMaster create(PosPurchaseMaster lookup) {
        return posPurchaseMasterRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosPurchaseMasterNotFoundException.class)
    @Override
    public PosPurchaseMaster update(PosPurchaseMaster updated) throws PosPurchaseMasterNotFoundException {
        PosPurchaseMaster posPurchaseMaster = posPurchaseMasterRepository.findOne(updated.getId());
        if (posPurchaseMaster == null) {
            throw new PosPurchaseMasterNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posPurchaseMaster);

        posPurchaseMaster.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posPurchaseMaster.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posPurchaseMaster);

        return posPurchaseMasterRepository.save(posPurchaseMaster);
    }

    @Transactional(rollbackFor = PosPurchaseMasterNotFoundException.class)
    @Override
    public PosPurchaseMaster copy(final PosPurchaseMaster copied) {

        PosPurchaseMaster posPurchaseMasterOrginal = posPurchaseMasterRepository.findOne(copied.getId());

        PosPurchaseMaster posPurchaseMaster = new PosPurchaseMaster();
        //BeanUtils.copyProperties(copied, posPurchaseMaster);
        //posPurchaseMaster.setId(null);

        posPurchaseMaster.getEmbdAuditable().setEntryBy(posPurchaseMasterOrginal.getEmbdAuditable().getEntryBy());
        posPurchaseMaster.getEmbdAuditable().setEntryDate(posPurchaseMasterOrginal.getEmbdAuditable().getEntryDate());
        posPurchaseMaster.getEmbdAuditable().setEditBy(posPurchaseMasterOrginal.getEmbdAuditable().getEditBy());
        posPurchaseMaster.getEmbdAuditable().setEditDate(posPurchaseMasterOrginal.getEmbdAuditable().getEditDate());
        posPurchaseMaster.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posPurchaseMaster.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posPurchaseMaster);

        for (PosPurchaseDetail currDet : posPurchaseMasterOrginal.getPosPurchaseDetails()) {

            PosPurchaseDetail det = new PosPurchaseDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setPosPurchaseMaster(posPurchaseMaster);
            if (posPurchaseMaster.getPosPurchaseDetails() == null) {
                posPurchaseMaster.setPosPurchaseDetails(new java.util.LinkedHashSet());
            }
            posPurchaseMaster.getPosPurchaseDetails().add(det);
        }


        return posPurchaseMasterRepository.save(posPurchaseMaster);
    }

    private void copyProperties(PosPurchaseMaster from, PosPurchaseMaster to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosSupplier(from.getPosSupplier());
        to.setPaidAmount(from.getPaidAmount());
        to.setPosPurchaseDetails(from.getPosPurchaseDetails());

    }

    @Override
    @Transactional
    public PosPurchaseMaster findById(BigInteger id) throws PosPurchaseMasterNotFoundException {
        PosPurchaseMaster posPurchaseMaster = posPurchaseMasterRepository.findOne(id);
        if (posPurchaseMaster == null) {
            throw new PosPurchaseMasterNotFoundException();
        }
        Hibernate.initialize(posPurchaseMaster.getPosPurchaseDetails());

        return posPurchaseMaster;
    }

    @Override
    @Transactional
    public Iterable<PosPurchaseMaster> findAll() {
        Iterable<PosPurchaseMaster> posPurchaseMasters = posPurchaseMasterRepository.findAll();

        for (PosPurchaseMaster posPurchaseMaster : posPurchaseMasters) {
            Hibernate.initialize(posPurchaseMaster.getPosPurchaseDetails());

        }

        return posPurchaseMasters;
    }

    @Transactional
    @Override
    public Iterable<PosPurchaseMaster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosPurchaseMaster> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosPurchaseMasterNotFoundException.class)
    public PosPurchaseMaster delete(BigInteger id) throws PosPurchaseMasterNotFoundException {

        PosPurchaseMaster posPurchaseMaster = posPurchaseMasterRepository.findOne(id);

        if (posPurchaseMaster == null) {
            throw new PosPurchaseMasterNotFoundException();
        }
        posPurchaseMasterRepository.delete(id);
        return posPurchaseMaster;
    }
}