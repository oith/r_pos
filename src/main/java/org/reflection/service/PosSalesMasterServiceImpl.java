package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSalesMasterNotFoundException;
import org.reflection.model.pos.PosSalesDetail;
import org.reflection.model.pos.PosSalesMaster;
import org.reflection.repositories.PosSalesMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posSalesMasterService")
@Transactional(readOnly = true)
public class PosSalesMasterServiceImpl implements PosSalesMasterService {

    @Autowired
    private PosSalesMasterRepository posSalesMasterRepository;

    @Transactional(readOnly = true)
    @Override
    public PosSalesMaster findByCode(String code) throws PosSalesMasterNotFoundException {
        return posSalesMasterRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosSalesMaster create(PosSalesMaster lookup) {
        return posSalesMasterRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosSalesMasterNotFoundException.class)
    @Override
    public PosSalesMaster update(PosSalesMaster updated) throws PosSalesMasterNotFoundException {
        PosSalesMaster posSalesMaster = posSalesMasterRepository.findOne(updated.getId());
        if (posSalesMaster == null) {
            throw new PosSalesMasterNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posSalesMaster);

        posSalesMaster.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posSalesMaster.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posSalesMaster);

        return posSalesMasterRepository.save(posSalesMaster);
    }

    @Transactional(rollbackFor = PosSalesMasterNotFoundException.class)
    @Override
    public PosSalesMaster copy(final PosSalesMaster copied) {

        PosSalesMaster posSalesMasterOrginal = posSalesMasterRepository.findOne(copied.getId());

        PosSalesMaster posSalesMaster = new PosSalesMaster();
        //BeanUtils.copyProperties(copied, posSalesMaster);
        //posSalesMaster.setId(null);

        posSalesMaster.getEmbdAuditable().setEntryBy(posSalesMasterOrginal.getEmbdAuditable().getEntryBy());
        posSalesMaster.getEmbdAuditable().setEntryDate(posSalesMasterOrginal.getEmbdAuditable().getEntryDate());
        posSalesMaster.getEmbdAuditable().setEditBy(posSalesMasterOrginal.getEmbdAuditable().getEditBy());
        posSalesMaster.getEmbdAuditable().setEditDate(posSalesMasterOrginal.getEmbdAuditable().getEditDate());
        posSalesMaster.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posSalesMaster.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posSalesMaster);

        for (PosSalesDetail currDet : posSalesMasterOrginal.getPosSalesDetails()) {

            PosSalesDetail det = new PosSalesDetail();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setPosSalesMaster(posSalesMaster);
            if (posSalesMaster.getPosSalesDetails() == null) {
                posSalesMaster.setPosSalesDetails(new java.util.LinkedHashSet());
            }
            posSalesMaster.getPosSalesDetails().add(det);
        }


        return posSalesMasterRepository.save(posSalesMaster);
    }

    private void copyProperties(PosSalesMaster from, PosSalesMaster to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosCustomer(from.getPosCustomer());
        to.setPaidAmount(from.getPaidAmount());
        to.setPosSalesDetails(from.getPosSalesDetails());

    }

    @Override
    @Transactional
    public PosSalesMaster findById(BigInteger id) throws PosSalesMasterNotFoundException {
        PosSalesMaster posSalesMaster = posSalesMasterRepository.findOne(id);
        if (posSalesMaster == null) {
            throw new PosSalesMasterNotFoundException();
        }
        Hibernate.initialize(posSalesMaster.getPosSalesDetails());

        return posSalesMaster;
    }

    @Override
    @Transactional
    public Iterable<PosSalesMaster> findAll() {
        Iterable<PosSalesMaster> posSalesMasters = posSalesMasterRepository.findAll();

        for (PosSalesMaster posSalesMaster : posSalesMasters) {
            Hibernate.initialize(posSalesMaster.getPosSalesDetails());

        }

        return posSalesMasters;
    }

    @Transactional
    @Override
    public Iterable<PosSalesMaster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosSalesMaster> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosSalesMasterNotFoundException.class)
    public PosSalesMaster delete(BigInteger id) throws PosSalesMasterNotFoundException {

        PosSalesMaster posSalesMaster = posSalesMasterRepository.findOne(id);

        if (posSalesMaster == null) {
            throw new PosSalesMasterNotFoundException();
        }
        posSalesMasterRepository.delete(id);
        return posSalesMaster;
    }
}