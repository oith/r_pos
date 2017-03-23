package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosCustomerNotFoundException;
import org.reflection.model.pos.PosCustomer;
import org.reflection.repositories.PosCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posCustomerService")
@Transactional(readOnly = true)
public class PosCustomerServiceImpl implements PosCustomerService {

    @Autowired
    private PosCustomerRepository posCustomerRepository;

    @Transactional(readOnly = true)
    @Override
    public PosCustomer findByMobile(String mobile) throws PosCustomerNotFoundException {
        return posCustomerRepository.findByMobile(mobile);
    }

    @Transactional
    @Override
    public PosCustomer create(PosCustomer lookup) {
        return posCustomerRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosCustomerNotFoundException.class)
    @Override
    public PosCustomer update(PosCustomer updated) throws PosCustomerNotFoundException {
        PosCustomer posCustomer = posCustomerRepository.findOne(updated.getId());
        if (posCustomer == null) {
            throw new PosCustomerNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posCustomer);


        copyProperties(updated, posCustomer);

        return posCustomerRepository.save(posCustomer);
    }

    @Transactional(rollbackFor = PosCustomerNotFoundException.class)
    @Override
    public PosCustomer copy(final PosCustomer copied) {

        PosCustomer posCustomerOrginal = posCustomerRepository.findOne(copied.getId());

        PosCustomer posCustomer = new PosCustomer();
        //BeanUtils.copyProperties(copied, posCustomer);
        //posCustomer.setId(null);


        copyProperties(copied, posCustomer);


        return posCustomerRepository.save(posCustomer);
    }

    private void copyProperties(PosCustomer from, PosCustomer to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setEmPosAnalysisCode(from.getEmPosAnalysisCode());
        to.setMobile(from.getMobile());
        to.setPic(from.getPic());
        to.setAddress(from.getAddress());
        to.setEmPosCustomerGroup(from.getEmPosCustomerGroup());

    }

    @Override
    @Transactional
    public PosCustomer findById(BigInteger id) throws PosCustomerNotFoundException {
        PosCustomer posCustomer = posCustomerRepository.findOne(id);
        if (posCustomer == null) {
            throw new PosCustomerNotFoundException();
        }

        return posCustomer;
    }

    @Override
    @Transactional
    public Iterable<PosCustomer> findAll() {
        Iterable<PosCustomer> posCustomers = posCustomerRepository.findAll();

        for (PosCustomer posCustomer : posCustomers) {

        }

        return posCustomers;
    }

    @Transactional
    @Override
    public Iterable<PosCustomer> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosCustomer> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosCustomerNotFoundException.class)
    public PosCustomer delete(BigInteger id) throws PosCustomerNotFoundException {

        PosCustomer posCustomer = posCustomerRepository.findOne(id);

        if (posCustomer == null) {
            throw new PosCustomerNotFoundException();
        }
        posCustomerRepository.delete(id);
        return posCustomer;
    }
}