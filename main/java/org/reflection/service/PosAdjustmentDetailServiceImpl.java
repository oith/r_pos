package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentDetailNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;
import org.reflection.repositories.PosAdjustmentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posAdjustmentDetailService")
@Transactional(readOnly = true)
public class PosAdjustmentDetailServiceImpl implements PosAdjustmentDetailService {

    @Autowired
    private PosAdjustmentDetailRepository posAdjustmentDetailRepository;


    @Transactional
    @Override
    public PosAdjustmentDetail create(PosAdjustmentDetail lookup) {
        return posAdjustmentDetailRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosAdjustmentDetailNotFoundException.class)
    @Override
    public PosAdjustmentDetail update(PosAdjustmentDetail updated) throws PosAdjustmentDetailNotFoundException {
        PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailRepository.findOne(updated.getId());
        if (posAdjustmentDetail == null) {
            throw new PosAdjustmentDetailNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posAdjustmentDetail);


        copyProperties(updated, posAdjustmentDetail);

        return posAdjustmentDetailRepository.save(posAdjustmentDetail);
    }

    @Transactional(rollbackFor = PosAdjustmentDetailNotFoundException.class)
    @Override
    public PosAdjustmentDetail copy(final PosAdjustmentDetail copied) {

        PosAdjustmentDetail posAdjustmentDetailOrginal = posAdjustmentDetailRepository.findOne(copied.getId());

        PosAdjustmentDetail posAdjustmentDetail = new PosAdjustmentDetail();
        //BeanUtils.copyProperties(copied, posAdjustmentDetail);
        //posAdjustmentDetail.setId(null);


        copyProperties(copied, posAdjustmentDetail);


        return posAdjustmentDetailRepository.save(posAdjustmentDetail);
    }

    private void copyProperties(PosAdjustmentDetail from, PosAdjustmentDetail to) {
        to.setPosAdjustmentMaster(from.getPosAdjustmentMaster());
        to.setPosProduct(from.getPosProduct());
        to.setQuantity(from.getQuantity());
        to.setUnitPrice(from.getUnitPrice());
        to.setLineTotal(from.getLineTotal());

    }

    @Override
    @Transactional
    public PosAdjustmentDetail findById(BigInteger id) throws PosAdjustmentDetailNotFoundException {
        PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailRepository.findOne(id);
        if (posAdjustmentDetail == null) {
            throw new PosAdjustmentDetailNotFoundException();
        }

        return posAdjustmentDetail;
    }

    @Override
    @Transactional
    public Iterable<PosAdjustmentDetail> findAll() {
        Iterable<PosAdjustmentDetail> posAdjustmentDetails = posAdjustmentDetailRepository.findAll();

        for (PosAdjustmentDetail posAdjustmentDetail : posAdjustmentDetails) {

        }

        return posAdjustmentDetails;
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosAdjustmentDetail> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosAdjustmentDetailNotFoundException.class)
    public PosAdjustmentDetail delete(BigInteger id) throws PosAdjustmentDetailNotFoundException {

        PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailRepository.findOne(id);

        if (posAdjustmentDetail == null) {
            throw new PosAdjustmentDetailNotFoundException();
        }
        posAdjustmentDetailRepository.delete(id);
        return posAdjustmentDetail;
    }
}