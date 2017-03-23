package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.pos.PosOpenBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosOpenBalanceRepository extends JpaRepository<PosOpenBalance, BigInteger> {

}
