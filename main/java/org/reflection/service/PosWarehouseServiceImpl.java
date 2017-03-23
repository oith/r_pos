package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosWarehouseNotFoundException;
import org.reflection.model.pos.PosWarehouse;
import org.reflection.repositories.PosWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posWarehouseService")
@Transactional(readOnly = true)
public class PosWarehouseServiceImpl implements PosWarehouseService {

    @Autowired
    private PosWarehouseRepository posWarehouseRepository;

    @Transactional(readOnly = true)
    @Override
    public PosWarehouse findByCode(String code) throws PosWarehouseNotFoundException {
        return posWarehouseRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosWarehouse create(PosWarehouse lookup) {
        return posWarehouseRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosWarehouseNotFoundException.class)
    @Override
    public PosWarehouse update(PosWarehouse updated) throws PosWarehouseNotFoundException {
        PosWarehouse posWarehouse = posWarehouseRepository.findOne(updated.getId());
        if (posWarehouse == null) {
            throw new PosWarehouseNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posWarehouse);


        copyProperties(updated, posWarehouse);

        return posWarehouseRepository.save(posWarehouse);
    }

    @Transactional(rollbackFor = PosWarehouseNotFoundException.class)
    @Override
    public PosWarehouse copy(final PosWarehouse copied) {

        PosWarehouse posWarehouseOrginal = posWarehouseRepository.findOne(copied.getId());

        PosWarehouse posWarehouse = new PosWarehouse();
        //BeanUtils.copyProperties(copied, posWarehouse);
        //posWarehouse.setId(null);


        copyProperties(copied, posWarehouse);


        return posWarehouseRepository.save(posWarehouse);
    }

    private void copyProperties(PosWarehouse from, PosWarehouse to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setAddress(from.getAddress());

    }

    @Override
    @Transactional
    public PosWarehouse findById(BigInteger id) throws PosWarehouseNotFoundException {
        PosWarehouse posWarehouse = posWarehouseRepository.findOne(id);
        if (posWarehouse == null) {
            throw new PosWarehouseNotFoundException();
        }

        return posWarehouse;
    }

    @Override
    @Transactional
    public Iterable<PosWarehouse> findAll() {
        Iterable<PosWarehouse> posWarehouses = posWarehouseRepository.findAll();

        for (PosWarehouse posWarehouse : posWarehouses) {

        }

        return posWarehouses;
    }

    @Transactional
    @Override
    public Iterable<PosWarehouse> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosWarehouse> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosWarehouseNotFoundException.class)
    public PosWarehouse delete(BigInteger id) throws PosWarehouseNotFoundException {

        PosWarehouse posWarehouse = posWarehouseRepository.findOne(id);

        if (posWarehouse == null) {
            throw new PosWarehouseNotFoundException();
        }
        posWarehouseRepository.delete(id);
        return posWarehouse;
    }
}