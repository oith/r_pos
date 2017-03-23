package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentPurchaseReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;
import org.reflection.model.pos.PosAdjustmentPurchaseReturnMaster;
import org.reflection.repositories.PosAdjustmentPurchaseReturnMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posAdjustmentPurchaseReturnMasterService")
@Transactional(readOnly = true)
public class PosAdjustmentPurchaseReturnMasterServiceImpl implements PosAdjustmentPurchaseReturnMasterService {

    @Autowired
    private PosAdjustmentPurchaseReturnMasterRepository posAdjustmentPurchaseReturnMasterRepository;

    @Transactional(readOnly = true)
    @Override
    public PosAdjustmentPurchaseReturnMaster findByCode(String code) throws PosAdjustmentPurchaseReturnMasterNotFoundException {
        return posAdjustmentPurchaseReturnMasterRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosAdjustmentPurchaseReturnMaster create(PosAdjustmentPurchaseReturnMaster lookup) {
        return posAdjustmentPurchaseReturnMasterRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosAdjustmentPurchaseReturnMasterNotFoundException.class)
    @Override
    public PosAdjustmentPurchaseReturnMaster update(PosAdjustmentPurchaseReturnMaster updated) throws PosAdjustmentPurchaseReturnMasterNotFoundException {
        PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster = posAdjustmentPurchaseReturnMasterRepository.findOne(updated.getId());
        if (posAdjustmentPurchaseReturnMaster == null) {
            throw new PosAdjustmentPurchaseReturnMasterNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posAdjustmentPurchaseReturnMaster);

        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posAdjustmentPurchaseReturnMaster);

        return posAdjustmentPurchaseReturnMasterRepository.save(posAdjustmentPurchaseReturnMaster);
    }

    @Transactional(rollbackFor = PosAdjustmentPurchaseReturnMasterNotFoundException.class)
    @Override
    public PosAdjustmentPurchaseReturnMaster copy(final PosAdjustmentPurchaseReturnMaster copied) {

        PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMasterOrginal = posAdjustmentPurchaseReturnMasterRepository.findOne(copied.getId());

        PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster = new PosAdjustmentPurchaseReturnMaster();
        //BeanUtils.copyProperties(copied, posAdjustmentPurchaseReturnMaster);
        //posAdjustmentPurchaseReturnMaster.setId(null);

        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEntryBy(posAdjustmentPurchaseReturnMasterOrginal.getEmbdAuditable().getEntryBy());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEntryDate(posAdjustmentPurchaseReturnMasterOrginal.getEmbdAuditable().getEntryDate());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEditBy(posAdjustmentPurchaseReturnMasterOrginal.getEmbdAuditable().getEditBy());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setEditDate(posAdjustmentPurchaseReturnMasterOrginal.getEmbdAuditable().getEditDate());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posAdjustmentPurchaseReturnMaster.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posAdjustmentPurchaseReturnMaster);

        for (PosAdjustmentDetail currDet : posAdjustmentPurchaseReturnMasterOrginal.getPosAdjustmentDetails()) {

            PosAdjustmentDetail det = new PosAdjustmentDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setPosAdjustmentMaster(posAdjustmentPurchaseReturnMaster);
            if (posAdjustmentPurchaseReturnMaster.getPosAdjustmentDetails() == null) {
                posAdjustmentPurchaseReturnMaster.setPosAdjustmentDetails(new java.util.LinkedHashSet());
            }
            posAdjustmentPurchaseReturnMaster.getPosAdjustmentDetails().add(det);
        }


        return posAdjustmentPurchaseReturnMasterRepository.save(posAdjustmentPurchaseReturnMaster);
    }

    private void copyProperties(PosAdjustmentPurchaseReturnMaster from, PosAdjustmentPurchaseReturnMaster to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosAdjustmentDetails(from.getPosAdjustmentDetails());
        to.setPosPurchaseMaster(from.getPosPurchaseMaster());

    }

    @Override
    @Transactional
    public PosAdjustmentPurchaseReturnMaster findById(BigInteger id) throws PosAdjustmentPurchaseReturnMasterNotFoundException {
        PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster = posAdjustmentPurchaseReturnMasterRepository.findOne(id);
        if (posAdjustmentPurchaseReturnMaster == null) {
            throw new PosAdjustmentPurchaseReturnMasterNotFoundException();
        }
        Hibernate.initialize(posAdjustmentPurchaseReturnMaster.getPosAdjustmentDetails());

        return posAdjustmentPurchaseReturnMaster;
    }

    @Override
    @Transactional
    public Iterable<PosAdjustmentPurchaseReturnMaster> findAll() {
        Iterable<PosAdjustmentPurchaseReturnMaster> posAdjustmentPurchaseReturnMasters = posAdjustmentPurchaseReturnMasterRepository.findAll();

        for (PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster : posAdjustmentPurchaseReturnMasters) {
            Hibernate.initialize(posAdjustmentPurchaseReturnMaster.getPosAdjustmentDetails());

        }

        return posAdjustmentPurchaseReturnMasters;
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentPurchaseReturnMaster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentPurchaseReturnMaster> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosAdjustmentPurchaseReturnMasterNotFoundException.class)
    public PosAdjustmentPurchaseReturnMaster delete(BigInteger id) throws PosAdjustmentPurchaseReturnMasterNotFoundException {

        PosAdjustmentPurchaseReturnMaster posAdjustmentPurchaseReturnMaster = posAdjustmentPurchaseReturnMasterRepository.findOne(id);

        if (posAdjustmentPurchaseReturnMaster == null) {
            throw new PosAdjustmentPurchaseReturnMasterNotFoundException();
        }
        posAdjustmentPurchaseReturnMasterRepository.delete(id);
        return posAdjustmentPurchaseReturnMaster;
    }
}