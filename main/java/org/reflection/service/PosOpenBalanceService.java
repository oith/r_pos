package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosOpenBalanceNotFoundException;
import org.reflection.model.pos.PosOpenBalance;

import java.math.BigInteger;

public interface PosOpenBalanceService {


    public PosOpenBalance findById(BigInteger id) throws PosOpenBalanceNotFoundException;

    public PosOpenBalance create(PosOpenBalance posOpenBalance);

    public PosOpenBalance update(PosOpenBalance posOpenBalance) throws PosOpenBalanceNotFoundException;

    public PosOpenBalance copy(PosOpenBalance posOpenBalance) throws PosOpenBalanceNotFoundException;

    public PosOpenBalance delete(BigInteger id) throws PosOpenBalanceNotFoundException;

    public Iterable<PosOpenBalance> search(_SearchDTO pageable);

    public Iterable<PosOpenBalance> findAll(_SearchDTO pageable);

    public Iterable<PosOpenBalance> findAll();
}