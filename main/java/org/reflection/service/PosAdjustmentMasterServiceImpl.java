package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;
import org.reflection.model.pos.PosAdjustmentMaster;
import org.reflection.repositories.PosAdjustmentMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posAdjustmentMasterService")
@Transactional(readOnly = true)
public class PosAdjustmentMasterServiceImpl implements PosAdjustmentMasterService {

    @Autowired
    private PosAdjustmentMasterRepository posAdjustmentMasterRepository;

    @Transactional(readOnly = true)
    @Override
    public PosAdjustmentMaster findByCode(String code) throws PosAdjustmentMasterNotFoundException {
        return posAdjustmentMasterRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosAdjustmentMaster create(PosAdjustmentMaster lookup) {
        return posAdjustmentMasterRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosAdjustmentMasterNotFoundException.class)
    @Override
    public PosAdjustmentMaster update(PosAdjustmentMaster updated) throws PosAdjustmentMasterNotFoundException {
        PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterRepository.findOne(updated.getId());
        if (posAdjustmentMaster == null) {
            throw new PosAdjustmentMasterNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posAdjustmentMaster);

        posAdjustmentMaster.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posAdjustmentMaster.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posAdjustmentMaster);

        return posAdjustmentMasterRepository.save(posAdjustmentMaster);
    }

    @Transactional(rollbackFor = PosAdjustmentMasterNotFoundException.class)
    @Override
    public PosAdjustmentMaster copy(final PosAdjustmentMaster copied) {

        PosAdjustmentMaster posAdjustmentMasterOrginal = posAdjustmentMasterRepository.findOne(copied.getId());

        PosAdjustmentMaster posAdjustmentMaster = new PosAdjustmentMaster();
        //BeanUtils.copyProperties(copied, posAdjustmentMaster);
        //posAdjustmentMaster.setId(null);

        posAdjustmentMaster.getEmbdAuditable().setEntryBy(posAdjustmentMasterOrginal.getEmbdAuditable().getEntryBy());
        posAdjustmentMaster.getEmbdAuditable().setEntryDate(posAdjustmentMasterOrginal.getEmbdAuditable().getEntryDate());
        posAdjustmentMaster.getEmbdAuditable().setEditBy(posAdjustmentMasterOrginal.getEmbdAuditable().getEditBy());
        posAdjustmentMaster.getEmbdAuditable().setEditDate(posAdjustmentMasterOrginal.getEmbdAuditable().getEditDate());
        posAdjustmentMaster.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posAdjustmentMaster.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posAdjustmentMaster);

        for (PosAdjustmentDetail currDet : posAdjustmentMasterOrginal.getPosAdjustmentDetails()) {

            PosAdjustmentDetail det = new PosAdjustmentDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setPosAdjustmentMaster(posAdjustmentMaster);
            if (posAdjustmentMaster.getPosAdjustmentDetails() == null) {
                posAdjustmentMaster.setPosAdjustmentDetails(new java.util.LinkedHashSet());
            }
            posAdjustmentMaster.getPosAdjustmentDetails().add(det);
        }


        return posAdjustmentMasterRepository.save(posAdjustmentMaster);
    }

    private void copyProperties(PosAdjustmentMaster from, PosAdjustmentMaster to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosAdjustmentDetails(from.getPosAdjustmentDetails());

    }

    @Override
    @Transactional
    public PosAdjustmentMaster findById(BigInteger id) throws PosAdjustmentMasterNotFoundException {
        PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterRepository.findOne(id);
        if (posAdjustmentMaster == null) {
            throw new PosAdjustmentMasterNotFoundException();
        }
        Hibernate.initialize(posAdjustmentMaster.getPosAdjustmentDetails());

        return posAdjustmentMaster;
    }

    @Override
    @Transactional
    public Iterable<PosAdjustmentMaster> findAll() {
        Iterable<PosAdjustmentMaster> posAdjustmentMasters = posAdjustmentMasterRepository.findAll();

        for (PosAdjustmentMaster posAdjustmentMaster : posAdjustmentMasters) {
            Hibernate.initialize(posAdjustmentMaster.getPosAdjustmentDetails());

        }

        return posAdjustmentMasters;
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentMaster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentMaster> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosAdjustmentMasterNotFoundException.class)
    public PosAdjustmentMaster delete(BigInteger id) throws PosAdjustmentMasterNotFoundException {

        PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterRepository.findOne(id);

        if (posAdjustmentMaster == null) {
            throw new PosAdjustmentMasterNotFoundException();
        }
        posAdjustmentMasterRepository.delete(id);
        return posAdjustmentMaster;
    }
}