package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosCollectionNotFoundException;
import org.reflection.model.pos.PosCollection;

import java.math.BigInteger;

public interface PosCollectionService {

    public PosCollection findByCode(String code) throws PosCollectionNotFoundException;

    public PosCollection findById(BigInteger id) throws PosCollectionNotFoundException;

    public PosCollection create(PosCollection posCollection);

    public PosCollection update(PosCollection posCollection) throws PosCollectionNotFoundException;

    public PosCollection copy(PosCollection posCollection) throws PosCollectionNotFoundException;

    public PosCollection delete(BigInteger id) throws PosCollectionNotFoundException;

    public Iterable<PosCollection> search(_SearchDTO pageable);

    public Iterable<PosCollection> findAll(_SearchDTO pageable);

    public Iterable<PosCollection> findAll();
}