package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosOpenBalanceNotFoundException;
import org.reflection.model.pos.PosOpenBalance;
import org.reflection.repositories.PosOpenBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posOpenBalanceService")
@Transactional(readOnly = true)
public class PosOpenBalanceServiceImpl implements PosOpenBalanceService {

    @Autowired
    private PosOpenBalanceRepository posOpenBalanceRepository;


    @Transactional
    @Override
    public PosOpenBalance create(PosOpenBalance lookup) {
        return posOpenBalanceRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosOpenBalanceNotFoundException.class)
    @Override
    public PosOpenBalance update(PosOpenBalance updated) throws PosOpenBalanceNotFoundException {
        PosOpenBalance posOpenBalance = posOpenBalanceRepository.findOne(updated.getId());
        if (posOpenBalance == null) {
            throw new PosOpenBalanceNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posOpenBalance);


        copyProperties(updated, posOpenBalance);

        return posOpenBalanceRepository.save(posOpenBalance);
    }

    @Transactional(rollbackFor = PosOpenBalanceNotFoundException.class)
    @Override
    public PosOpenBalance copy(final PosOpenBalance copied) {

        PosOpenBalance posOpenBalanceOrginal = posOpenBalanceRepository.findOne(copied.getId());

        PosOpenBalance posOpenBalance = new PosOpenBalance();
        //BeanUtils.copyProperties(copied, posOpenBalance);
        //posOpenBalance.setId(null);


        copyProperties(copied, posOpenBalance);


        return posOpenBalanceRepository.save(posOpenBalance);
    }

    private void copyProperties(PosOpenBalance from, PosOpenBalance to) {
        to.setOnDate(from.getOnDate());
        to.setPosProduct(from.getPosProduct());
        to.setQuantity(from.getQuantity());
        to.setEmSign(from.getEmSign());
        to.setAmount(from.getAmount());

    }

    @Override
    @Transactional
    public PosOpenBalance findById(BigInteger id) throws PosOpenBalanceNotFoundException {
        PosOpenBalance posOpenBalance = posOpenBalanceRepository.findOne(id);
        if (posOpenBalance == null) {
            throw new PosOpenBalanceNotFoundException();
        }

        return posOpenBalance;
    }

    @Override
    @Transactional
    public Iterable<PosOpenBalance> findAll() {
        Iterable<PosOpenBalance> posOpenBalances = posOpenBalanceRepository.findAll();

        for (PosOpenBalance posOpenBalance : posOpenBalances) {

        }

        return posOpenBalances;
    }

    @Transactional
    @Override
    public Iterable<PosOpenBalance> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosOpenBalance> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosOpenBalanceNotFoundException.class)
    public PosOpenBalance delete(BigInteger id) throws PosOpenBalanceNotFoundException {

        PosOpenBalance posOpenBalance = posOpenBalanceRepository.findOne(id);

        if (posOpenBalance == null) {
            throw new PosOpenBalanceNotFoundException();
        }
        posOpenBalanceRepository.delete(id);
        return posOpenBalance;
    }
}