package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentSalesReturnMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;
import org.reflection.model.pos.PosAdjustmentSalesReturnMaster;
import org.reflection.repositories.PosAdjustmentSalesReturnMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posAdjustmentSalesReturnMasterService")
@Transactional(readOnly = true)
public class PosAdjustmentSalesReturnMasterServiceImpl implements PosAdjustmentSalesReturnMasterService {

    @Autowired
    private PosAdjustmentSalesReturnMasterRepository posAdjustmentSalesReturnMasterRepository;

    @Transactional(readOnly = true)
    @Override
    public PosAdjustmentSalesReturnMaster findByCode(String code) throws PosAdjustmentSalesReturnMasterNotFoundException {
        return posAdjustmentSalesReturnMasterRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosAdjustmentSalesReturnMaster create(PosAdjustmentSalesReturnMaster lookup) {
        return posAdjustmentSalesReturnMasterRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosAdjustmentSalesReturnMasterNotFoundException.class)
    @Override
    public PosAdjustmentSalesReturnMaster update(PosAdjustmentSalesReturnMaster updated) throws PosAdjustmentSalesReturnMasterNotFoundException {
        PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster = posAdjustmentSalesReturnMasterRepository.findOne(updated.getId());
        if (posAdjustmentSalesReturnMaster == null) {
            throw new PosAdjustmentSalesReturnMasterNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posAdjustmentSalesReturnMaster);

        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posAdjustmentSalesReturnMaster);

        return posAdjustmentSalesReturnMasterRepository.save(posAdjustmentSalesReturnMaster);
    }

    @Transactional(rollbackFor = PosAdjustmentSalesReturnMasterNotFoundException.class)
    @Override
    public PosAdjustmentSalesReturnMaster copy(final PosAdjustmentSalesReturnMaster copied) {

        PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMasterOrginal = posAdjustmentSalesReturnMasterRepository.findOne(copied.getId());

        PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster = new PosAdjustmentSalesReturnMaster();
        //BeanUtils.copyProperties(copied, posAdjustmentSalesReturnMaster);
        //posAdjustmentSalesReturnMaster.setId(null);

        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEntryBy(posAdjustmentSalesReturnMasterOrginal.getEmbdAuditable().getEntryBy());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEntryDate(posAdjustmentSalesReturnMasterOrginal.getEmbdAuditable().getEntryDate());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEditBy(posAdjustmentSalesReturnMasterOrginal.getEmbdAuditable().getEditBy());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setEditDate(posAdjustmentSalesReturnMasterOrginal.getEmbdAuditable().getEditDate());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posAdjustmentSalesReturnMaster.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posAdjustmentSalesReturnMaster);

        for (PosAdjustmentDetail currDet : posAdjustmentSalesReturnMasterOrginal.getPosAdjustmentDetails()) {

            PosAdjustmentDetail det = new PosAdjustmentDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setPosAdjustmentMaster(posAdjustmentSalesReturnMaster);
            if (posAdjustmentSalesReturnMaster.getPosAdjustmentDetails() == null) {
                posAdjustmentSalesReturnMaster.setPosAdjustmentDetails(new java.util.LinkedHashSet());
            }
            posAdjustmentSalesReturnMaster.getPosAdjustmentDetails().add(det);
        }


        return posAdjustmentSalesReturnMasterRepository.save(posAdjustmentSalesReturnMaster);
    }

    private void copyProperties(PosAdjustmentSalesReturnMaster from, PosAdjustmentSalesReturnMaster to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosAdjustmentDetails(from.getPosAdjustmentDetails());
        to.setPosSalesMaster(from.getPosSalesMaster());

    }

    @Override
    @Transactional
    public PosAdjustmentSalesReturnMaster findById(BigInteger id) throws PosAdjustmentSalesReturnMasterNotFoundException {
        PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster = posAdjustmentSalesReturnMasterRepository.findOne(id);
        if (posAdjustmentSalesReturnMaster == null) {
            throw new PosAdjustmentSalesReturnMasterNotFoundException();
        }
        Hibernate.initialize(posAdjustmentSalesReturnMaster.getPosAdjustmentDetails());

        return posAdjustmentSalesReturnMaster;
    }

    @Override
    @Transactional
    public Iterable<PosAdjustmentSalesReturnMaster> findAll() {
        Iterable<PosAdjustmentSalesReturnMaster> posAdjustmentSalesReturnMasters = posAdjustmentSalesReturnMasterRepository.findAll();

        for (PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster : posAdjustmentSalesReturnMasters) {
            Hibernate.initialize(posAdjustmentSalesReturnMaster.getPosAdjustmentDetails());

        }

        return posAdjustmentSalesReturnMasters;
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentSalesReturnMaster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentSalesReturnMaster> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosAdjustmentSalesReturnMasterNotFoundException.class)
    public PosAdjustmentSalesReturnMaster delete(BigInteger id) throws PosAdjustmentSalesReturnMasterNotFoundException {

        PosAdjustmentSalesReturnMaster posAdjustmentSalesReturnMaster = posAdjustmentSalesReturnMasterRepository.findOne(id);

        if (posAdjustmentSalesReturnMaster == null) {
            throw new PosAdjustmentSalesReturnMasterNotFoundException();
        }
        posAdjustmentSalesReturnMasterRepository.delete(id);
        return posAdjustmentSalesReturnMaster;
    }
}