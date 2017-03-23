package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxChooseNotFoundException;
import org.reflection.model.sample.ZxChoose;

import java.math.BigInteger;

public interface ZxChooseService {


    public ZxChoose findById(BigInteger id) throws ZxChooseNotFoundException;

    public ZxChoose create(ZxChoose zxChoose);

    public ZxChoose update(ZxChoose zxChoose) throws ZxChooseNotFoundException;

    public ZxChoose copy(ZxChoose zxChoose) throws ZxChooseNotFoundException;

    public ZxChoose delete(BigInteger id) throws ZxChooseNotFoundException;

    public Iterable<ZxChoose> search(_SearchDTO pageable);

    public Iterable<ZxChoose> findAll(_SearchDTO pageable);

    public Iterable<ZxChoose> findAll();
}