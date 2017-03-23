package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxAnyObjectNotFoundException;
import org.reflection.model.sample.ZxAnyObject;

import java.math.BigInteger;

public interface ZxAnyObjectService {


    public ZxAnyObject findById(BigInteger id) throws ZxAnyObjectNotFoundException;

    public ZxAnyObject create(ZxAnyObject zxAnyObject);

    public ZxAnyObject update(ZxAnyObject zxAnyObject) throws ZxAnyObjectNotFoundException;

    public ZxAnyObject copy(ZxAnyObject zxAnyObject) throws ZxAnyObjectNotFoundException;

    public ZxAnyObject delete(BigInteger id) throws ZxAnyObjectNotFoundException;

    public Iterable<ZxAnyObject> search(_SearchDTO pageable);

    public Iterable<ZxAnyObject> findAll(_SearchDTO pageable);

    public Iterable<ZxAnyObject> findAll();
}