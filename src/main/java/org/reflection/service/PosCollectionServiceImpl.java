package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosCollectionNotFoundException;
import org.reflection.model.pos.PosCollection;
import org.reflection.repositories.PosCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posCollectionService")
@Transactional(readOnly = true)
public class PosCollectionServiceImpl implements PosCollectionService {

    @Autowired
    private PosCollectionRepository posCollectionRepository;

    @Transactional(readOnly = true)
    @Override
    public PosCollection findByCode(String code) throws PosCollectionNotFoundException {
        return posCollectionRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosCollection create(PosCollection lookup) {
        return posCollectionRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosCollectionNotFoundException.class)
    @Override
    public PosCollection update(PosCollection updated) throws PosCollectionNotFoundException {
        PosCollection posCollection = posCollectionRepository.findOne(updated.getId());
        if (posCollection == null) {
            throw new PosCollectionNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posCollection);

        posCollection.getEmbdAuditable().setEditBy(updated.getEmbdAuditable().getEditBy());
        posCollection.getEmbdAuditable().setEditDate(updated.getEmbdAuditable().getEditDate());


        copyProperties(updated, posCollection);

        return posCollectionRepository.save(posCollection);
    }

    @Transactional(rollbackFor = PosCollectionNotFoundException.class)
    @Override
    public PosCollection copy(final PosCollection copied) {

        PosCollection posCollectionOrginal = posCollectionRepository.findOne(copied.getId());

        PosCollection posCollection = new PosCollection();
        //BeanUtils.copyProperties(copied, posCollection);
        //posCollection.setId(null);

        posCollection.getEmbdAuditable().setEntryBy(posCollectionOrginal.getEmbdAuditable().getEntryBy());
        posCollection.getEmbdAuditable().setEntryDate(posCollectionOrginal.getEmbdAuditable().getEntryDate());
        posCollection.getEmbdAuditable().setEditBy(posCollectionOrginal.getEmbdAuditable().getEditBy());
        posCollection.getEmbdAuditable().setEditDate(posCollectionOrginal.getEmbdAuditable().getEditDate());
        posCollection.getEmbdAuditable().setCopyBy(copied.getEmbdAuditable().getCopyBy());
        posCollection.getEmbdAuditable().setCopyDate(copied.getEmbdAuditable().getCopyDate());


        copyProperties(copied, posCollection);


        return posCollectionRepository.save(posCollection);
    }

    private void copyProperties(PosCollection from, PosCollection to) {
        to.setCode(from.getCode());
        to.setTransDate(from.getTransDate());
        to.setOriginDate(from.getOriginDate());
        to.setAmount(from.getAmount());
        to.setRemarks(from.getRemarks());
        to.setAuthUserTransBy(from.getAuthUserTransBy());
        to.setPosSalesMaster(from.getPosSalesMaster());
        to.setPosCustomer(from.getPosCustomer());
        to.setEmPosCollectionType(from.getEmPosCollectionType());

    }

    @Override
    @Transactional
    public PosCollection findById(BigInteger id) throws PosCollectionNotFoundException {
        PosCollection posCollection = posCollectionRepository.findOne(id);
        if (posCollection == null) {
            throw new PosCollectionNotFoundException();
        }

        return posCollection;
    }

    @Override
    @Transactional
    public Iterable<PosCollection> findAll() {
        Iterable<PosCollection> posCollections = posCollectionRepository.findAll();

        for (PosCollection posCollection : posCollections) {

        }

        return posCollections;
    }

    @Transactional
    @Override
    public Iterable<PosCollection> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosCollection> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosCollectionNotFoundException.class)
    public PosCollection delete(BigInteger id) throws PosCollectionNotFoundException {

        PosCollection posCollection = posCollectionRepository.findOne(id);

        if (posCollection == null) {
            throw new PosCollectionNotFoundException();
        }
        posCollectionRepository.delete(id);
        return posCollection;
    }
}