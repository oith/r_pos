package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpTrainingDtlNotFoundException;
import org.reflection.model.sample.ZxEmpTrainingDtl;

import java.math.BigInteger;

public interface ZxEmpTrainingDtlService {


    public ZxEmpTrainingDtl findById(BigInteger id) throws ZxEmpTrainingDtlNotFoundException;

    public ZxEmpTrainingDtl create(ZxEmpTrainingDtl zxEmpTrainingDtl);

    public ZxEmpTrainingDtl update(ZxEmpTrainingDtl zxEmpTrainingDtl) throws ZxEmpTrainingDtlNotFoundException;

    public ZxEmpTrainingDtl copy(ZxEmpTrainingDtl zxEmpTrainingDtl) throws ZxEmpTrainingDtlNotFoundException;

    public ZxEmpTrainingDtl delete(BigInteger id) throws ZxEmpTrainingDtlNotFoundException;

    public Iterable<ZxEmpTrainingDtl> search(_SearchDTO pageable);

    public Iterable<ZxEmpTrainingDtl> findAll(_SearchDTO pageable);

    public Iterable<ZxEmpTrainingDtl> findAll();
}