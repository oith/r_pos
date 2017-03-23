package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseDetailNotFoundException;
import org.reflection.model.pos.PosPurchaseDetail;
import org.reflection.repositories.PosPurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posPurchaseDetailService")
@Transactional(readOnly = true)
public class PosPurchaseDetailServiceImpl implements PosPurchaseDetailService {

    @Autowired
    private PosPurchaseDetailRepository posPurchaseDetailRepository;


    @Transactional
    @Override
    public PosPurchaseDetail create(PosPurchaseDetail lookup) {
        return posPurchaseDetailRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosPurchaseDetailNotFoundException.class)
    @Override
    public PosPurchaseDetail update(PosPurchaseDetail updated) throws PosPurchaseDetailNotFoundException {
        PosPurchaseDetail posPurchaseDetail = posPurchaseDetailRepository.findOne(updated.getId());
        if (posPurchaseDetail == null) {
            throw new PosPurchaseDetailNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posPurchaseDetail);


        copyProperties(updated, posPurchaseDetail);

        return posPurchaseDetailRepository.save(posPurchaseDetail);
    }

    @Transactional(rollbackFor = PosPurchaseDetailNotFoundException.class)
    @Override
    public PosPurchaseDetail copy(final PosPurchaseDetail copied) {

        PosPurchaseDetail posPurchaseDetailOrginal = posPurchaseDetailRepository.findOne(copied.getId());

        PosPurchaseDetail posPurchaseDetail = new PosPurchaseDetail();
        //BeanUtils.copyProperties(copied, posPurchaseDetail);
        //posPurchaseDetail.setId(null);


        copyProperties(copied, posPurchaseDetail);


        return posPurchaseDetailRepository.save(posPurchaseDetail);
    }

    private void copyProperties(PosPurchaseDetail from, PosPurchaseDetail to) {
        to.setPosPurchaseMaster(from.getPosPurchaseMaster());
        to.setPosProduct(from.getPosProduct());
        to.setQuantity(from.getQuantity());
        to.setUnitPrice(from.getUnitPrice());
        to.setLineTotal(from.getLineTotal());

    }

    @Override
    @Transactional
    public PosPurchaseDetail findById(BigInteger id) throws PosPurchaseDetailNotFoundException {
        PosPurchaseDetail posPurchaseDetail = posPurchaseDetailRepository.findOne(id);
        if (posPurchaseDetail == null) {
            throw new PosPurchaseDetailNotFoundException();
        }

        return posPurchaseDetail;
    }

    @Override
    @Transactional
    public Iterable<PosPurchaseDetail> findAll() {
        Iterable<PosPurchaseDetail> posPurchaseDetails = posPurchaseDetailRepository.findAll();

        for (PosPurchaseDetail posPurchaseDetail : posPurchaseDetails) {

        }

        return posPurchaseDetails;
    }

    @Transactional
    @Override
    public Iterable<PosPurchaseDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosPurchaseDetail> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosPurchaseDetailNotFoundException.class)
    public PosPurchaseDetail delete(BigInteger id) throws PosPurchaseDetailNotFoundException {

        PosPurchaseDetail posPurchaseDetail = posPurchaseDetailRepository.findOne(id);

        if (posPurchaseDetail == null) {
            throw new PosPurchaseDetailNotFoundException();
        }
        posPurchaseDetailRepository.delete(id);
        return posPurchaseDetail;
    }
}