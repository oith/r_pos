package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosInvMovementNotFoundException;
import org.reflection.model.pos.PosInvMovement;

import java.math.BigInteger;

public interface PosInvMovementService {

    public PosInvMovement findByCode(String code) throws PosInvMovementNotFoundException;

    public PosInvMovement findById(BigInteger id) throws PosInvMovementNotFoundException;

    public PosInvMovement create(PosInvMovement posInvMovement);

    public PosInvMovement update(PosInvMovement posInvMovement) throws PosInvMovementNotFoundException;

    public PosInvMovement copy(PosInvMovement posInvMovement) throws PosInvMovementNotFoundException;

    public PosInvMovement delete(BigInteger id) throws PosInvMovementNotFoundException;

    public Iterable<PosInvMovement> search(_SearchDTO pageable);

    public Iterable<PosInvMovement> findAll(_SearchDTO pageable);

    public Iterable<PosInvMovement> findAll();
}