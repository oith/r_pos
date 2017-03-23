package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSalesDetailNotFoundException;
import org.reflection.model.pos.PosSalesDetail;
import org.reflection.repositories.PosSalesDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posSalesDetailService")
@Transactional(readOnly = true)
public class PosSalesDetailServiceImpl implements PosSalesDetailService {

    @Autowired
    private PosSalesDetailRepository posSalesDetailRepository;


    @Transactional
    @Override
    public PosSalesDetail create(PosSalesDetail lookup) {
        return posSalesDetailRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosSalesDetailNotFoundException.class)
    @Override
    public PosSalesDetail update(PosSalesDetail updated) throws PosSalesDetailNotFoundException {
        PosSalesDetail posSalesDetail = posSalesDetailRepository.findOne(updated.getId());
        if (posSalesDetail == null) {
            throw new PosSalesDetailNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posSalesDetail);


        copyProperties(updated, posSalesDetail);

        return posSalesDetailRepository.save(posSalesDetail);
    }

    @Transactional(rollbackFor = PosSalesDetailNotFoundException.class)
    @Override
    public PosSalesDetail copy(final PosSalesDetail copied) {

        PosSalesDetail posSalesDetailOrginal = posSalesDetailRepository.findOne(copied.getId());

        PosSalesDetail posSalesDetail = new PosSalesDetail();
        //BeanUtils.copyProperties(copied, posSalesDetail);
        //posSalesDetail.setId(null);


        copyProperties(copied, posSalesDetail);


        return posSalesDetailRepository.save(posSalesDetail);
    }

    private void copyProperties(PosSalesDetail from, PosSalesDetail to) {
        to.setPosSalesMaster(from.getPosSalesMaster());
        to.setPosProduct(from.getPosProduct());
        to.setQuantity(from.getQuantity());
        to.setUnitPrice(from.getUnitPrice());
        to.setLineTotal(from.getLineTotal());

    }

    @Override
    @Transactional
    public PosSalesDetail findById(BigInteger id) throws PosSalesDetailNotFoundException {
        PosSalesDetail posSalesDetail = posSalesDetailRepository.findOne(id);
        if (posSalesDetail == null) {
            throw new PosSalesDetailNotFoundException();
        }

        return posSalesDetail;
    }

    @Override
    @Transactional
    public Iterable<PosSalesDetail> findAll() {
        Iterable<PosSalesDetail> posSalesDetails = posSalesDetailRepository.findAll();

        for (PosSalesDetail posSalesDetail : posSalesDetails) {

        }

        return posSalesDetails;
    }

    @Transactional
    @Override
    public Iterable<PosSalesDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosSalesDetail> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosSalesDetailNotFoundException.class)
    public PosSalesDetail delete(BigInteger id) throws PosSalesDetailNotFoundException {

        PosSalesDetail posSalesDetail = posSalesDetailRepository.findOne(id);

        if (posSalesDetail == null) {
            throw new PosSalesDetailNotFoundException();
        }
        posSalesDetailRepository.delete(id);
        return posSalesDetail;
    }
}