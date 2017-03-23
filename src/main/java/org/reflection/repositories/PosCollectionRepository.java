package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.exception.PosCollectionNotFoundException;
import org.reflection.model.pos.PosCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosCollectionRepository extends JpaRepository<PosCollection, BigInteger> {
    public PosCollection findByCode(String code) throws PosCollectionNotFoundException;
}
