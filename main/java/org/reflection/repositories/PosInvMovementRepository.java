package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosInvMovementNotFoundException;
import org.reflection.model.pos.PosInvMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosInvMovementRepository extends JpaRepository<PosInvMovement, BigInteger> {
    public PosInvMovement findByCode(String code) throws PosInvMovementNotFoundException;
}
