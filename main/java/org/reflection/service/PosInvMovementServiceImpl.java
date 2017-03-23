package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosInvMovementNotFoundException;
import org.reflection.model.pos.PosInvMovement;
import org.reflection.repositories.PosInvMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posInvMovementService")
@Transactional(readOnly = true)
public class PosInvMovementServiceImpl implements PosInvMovementService {

    @Autowired
    private PosInvMovementRepository posInvMovementRepository;

    @Transactional(readOnly = true)
    @Override
    public PosInvMovement findByCode(String code) throws PosInvMovementNotFoundException {
        return posInvMovementRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosInvMovement create(PosInvMovement lookup) {
        return posInvMovementRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosInvMovementNotFoundException.class)
    @Override
    public PosInvMovement update(PosInvMovement updated) throws PosInvMovementNotFoundException {
        PosInvMovement posInvMovement = posInvMovementRepository.findOne(updated.getId());
        if (posInvMovement == null) {
            throw new PosInvMovementNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posInvMovement);

        posInvMovement.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posInvMovement.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posInvMovement);

        return posInvMovementRepository.save(posInvMovement);
    }

    @Transactional(rollbackFor = PosInvMovementNotFoundException.class)
    @Override
    public PosInvMovement copy(final PosInvMovement copied) {

        PosInvMovement posInvMovementOrginal = posInvMovementRepository.findOne(copied.getId());

        PosInvMovement posInvMovement = new PosInvMovement();
        //BeanUtils.copyProperties(copied, posInvMovement);
        //posInvMovement.setId(null);

        posInvMovement.getEmbdAuditable().setEntryBy(posInvMovementOrginal.getEmbdAuditable().getEntryBy());
        posInvMovement.getEmbdAuditable().setEntryDate(posInvMovementOrginal.getEmbdAuditable().getEntryDate());
        posInvMovement.getEmbdAuditable().setEditBy(posInvMovementOrginal.getEmbdAuditable().getEditBy());
        posInvMovement.getEmbdAuditable().setEditDate(posInvMovementOrginal.getEmbdAuditable().getEditDate());
        posInvMovement.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posInvMovement.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posInvMovement);


        return posInvMovementRepository.save(posInvMovement);
    }

    private void copyProperties(PosInvMovement from, PosInvMovement to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setQuantity(from.getQuantity());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosProduct(from.getPosProduct());
        to.setPosWarehouseFrom(from.getPosWarehouseFrom());
        to.setPosWarehouseTo(from.getPosWarehouseTo());

    }

    @Override
    @Transactional
    public PosInvMovement findById(BigInteger id) throws PosInvMovementNotFoundException {
        PosInvMovement posInvMovement = posInvMovementRepository.findOne(id);
        if (posInvMovement == null) {
            throw new PosInvMovementNotFoundException();
        }

        return posInvMovement;
    }

    @Override
    @Transactional
    public Iterable<PosInvMovement> findAll() {
        Iterable<PosInvMovement> posInvMovements = posInvMovementRepository.findAll();

        for (PosInvMovement posInvMovement : posInvMovements) {

        }

        return posInvMovements;
    }

    @Transactional
    @Override
    public Iterable<PosInvMovement> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosInvMovement> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosInvMovementNotFoundException.class)
    public PosInvMovement delete(BigInteger id) throws PosInvMovementNotFoundException {

        PosInvMovement posInvMovement = posInvMovementRepository.findOne(id);

        if (posInvMovement == null) {
            throw new PosInvMovementNotFoundException();
        }
        posInvMovementRepository.delete(id);
        return posInvMovement;
    }
}