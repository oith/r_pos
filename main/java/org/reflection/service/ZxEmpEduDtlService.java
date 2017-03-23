package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpEduDtlNotFoundException;
import org.reflection.model.sample.ZxEmpEduDtl;

import java.math.BigInteger;

public interface ZxEmpEduDtlService {


    public ZxEmpEduDtl findById(BigInteger id) throws ZxEmpEduDtlNotFoundException;

    public ZxEmpEduDtl create(ZxEmpEduDtl zxEmpEduDtl);

    public ZxEmpEduDtl update(ZxEmpEduDtl zxEmpEduDtl) throws ZxEmpEduDtlNotFoundException;

    public ZxEmpEduDtl copy(ZxEmpEduDtl zxEmpEduDtl) throws ZxEmpEduDtlNotFoundException;

    public ZxEmpEduDtl delete(BigInteger id) throws ZxEmpEduDtlNotFoundException;

    public Iterable<ZxEmpEduDtl> search(_SearchDTO pageable);

    public Iterable<ZxEmpEduDtl> findAll(_SearchDTO pageable);

    public Iterable<ZxEmpEduDtl> findAll();
}