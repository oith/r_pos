package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosProductNotFoundException;
import org.reflection.model.pos.PosProduct;
import org.reflection.repositories.PosProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("posProductService")
@Transactional(readOnly = true)
public class PosProductServiceImpl implements PosProductService {

    @Autowired
    private PosProductRepository posProductRepository;

    @Transactional(readOnly = true)
    @Override
    public PosProduct findByCode(String code) throws PosProductNotFoundException {
        return posProductRepository.findByCode(code);
    }

    @Transactional
    @Override
    public PosProduct create(PosProduct lookup) {
        return posProductRepository.save(lookup);
    }

    @Transactional(rollbackFor = PosProductNotFoundException.class)
    @Override
    public PosProduct update(PosProduct updated) throws PosProductNotFoundException {
        PosProduct posProduct = posProductRepository.findOne(updated.getId());
        if (posProduct == null) {
            throw new PosProductNotFoundException();
        }
        //BeanUtils.copyProperties(updated, posProduct);


        copyProperties(updated, posProduct);

        return posProductRepository.save(posProduct);
    }

    @Transactional(rollbackFor = PosProductNotFoundException.class)
    @Override
    public PosProduct copy(final PosProduct copied) {

        PosProduct posProductOrginal = posProductRepository.findOne(copied.getId());

        PosProduct posProduct = new PosProduct();
        //BeanUtils.copyProperties(copied, posProduct);
        //posProduct.setId(null);


        copyProperties(copied, posProduct);


        return posProductRepository.save(posProduct);
    }

    private void copyProperties(PosProduct from, PosProduct to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setTagCode(from.getTagCode());
        to.setIsVatCalc(from.getIsVatCalc());
        to.setLimitMax(from.getLimitMax());
        to.setLimitStd(from.getLimitStd());
        to.setLimitMin(from.getLimitMin());
        to.setUnitPricePurchaseStd(from.getUnitPricePurchaseStd());
        to.setUnitPricePurchaseMin(from.getUnitPricePurchaseMin());
        to.setUnitPricePurchaseMax(from.getUnitPricePurchaseMax());
        to.setUnitPriceSalesStd(from.getUnitPriceSalesStd());
        to.setUnitPriceSalesMin(from.getUnitPriceSalesMin());
        to.setUnitPriceSalesMax(from.getUnitPriceSalesMax());
        to.setDescription(from.getDescription());
        to.setAcOne(from.getAcOne());
        to.setAcTwo(from.getAcTwo());
        to.setAcThree(from.getAcThree());
        to.setAcFour(from.getAcFour());
        to.setAcFive(from.getAcFive());

    }

    @Override
    @Transactional
    public PosProduct findById(BigInteger id) throws PosProductNotFoundException {
        PosProduct posProduct = posProductRepository.findOne(id);
        if (posProduct == null) {
            throw new PosProductNotFoundException();
        }

        return posProduct;
    }

    @Override
    @Transactional
    public Iterable<PosProduct> findAll() {
        Iterable<PosProduct> posProducts = posProductRepository.findAll();

        for (PosProduct posProduct : posProducts) {

        }

        return posProducts;
    }

    @Transactional
    @Override
    public Iterable<PosProduct> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<PosProduct> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = PosProductNotFoundException.class)
    public PosProduct delete(BigInteger id) throws PosProductNotFoundException {

        PosProduct posProduct = posProductRepository.findOne(id);

        if (posProduct == null) {
            throw new PosProductNotFoundException();
        }
        posProductRepository.delete(id);
        return posProduct;
    }
}