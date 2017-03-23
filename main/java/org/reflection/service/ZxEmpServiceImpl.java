package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.model.sample.ZxEmp;
import org.reflection.model.sample.ZxEmpEduDtl;
import org.reflection.model.sample.ZxEmpTrainingDtl;
import org.reflection.repositories.ZxEmpRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("zxEmpService")
@Transactional(readOnly = true)
public class ZxEmpServiceImpl implements ZxEmpService {

    @Autowired
    private ZxEmpRepository zxEmpRepository;

    @Transactional(readOnly = true)
    @Override
    public ZxEmp findByCode(String code) throws ZxEmpNotFoundException {
        return zxEmpRepository.findByCode(code);
    }

    @Transactional
    @Override
    public ZxEmp create(ZxEmp lookup) {
        return zxEmpRepository.save(lookup);
    }

    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp update(ZxEmp updated) throws ZxEmpNotFoundException {
        ZxEmp zxEmp = zxEmpRepository.findOne(updated.getId());
        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }
        //BeanUtils.copyProperties(updated, zxEmp);


        copyProperties(updated, zxEmp);

        return zxEmpRepository.save(zxEmp);
    }

    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    @Override
    public ZxEmp copy(final ZxEmp copied) {

        ZxEmp zxEmpOrginal = zxEmpRepository.findOne(copied.getId());

        ZxEmp zxEmp = new ZxEmp();
        //BeanUtils.copyProperties(copied, zxEmp);
        //zxEmp.setId(null);


        copyProperties(copied, zxEmp);

        for (ZxEmpEduDtl currDet : zxEmpOrginal.getZxEmpEduDtls()) {

            ZxEmpEduDtl det = new ZxEmpEduDtl();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setZxEmp(zxEmp);
            if (zxEmp.getZxEmpEduDtls() == null) {
                zxEmp.setZxEmpEduDtls(new java.util.ArrayList());
            }
            zxEmp.getZxEmpEduDtls().add(det);
        }
        for (ZxEmpTrainingDtl currDet : zxEmpOrginal.getZxEmpTrainingDtls()) {

            ZxEmpTrainingDtl det = new ZxEmpTrainingDtl();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setZxEmp(zxEmp);
            if (zxEmp.getZxEmpTrainingDtls() == null) {
                zxEmp.setZxEmpTrainingDtls(new java.util.ArrayList());
            }
            zxEmp.getZxEmpTrainingDtls().add(det);
        }


        return zxEmpRepository.save(zxEmp);
    }

    private void copyProperties(ZxEmp from, ZxEmp to) {
        to.setCode(from.getCode());
        to.setTagCode(from.getTagCode());
        to.setPic(from.getPic());
        to.setFullName(from.getFullName());
        to.setGender(from.getGender());
        to.setDob(from.getDob());
        to.setSalary(from.getSalary());
        to.setTaxPaid(from.getTaxPaid());
        to.setEmail(from.getEmail());
        to.setPin(from.getPin());
        to.setWebAddress(from.getWebAddress());
        to.setDocument(from.getDocument());
        to.setIsActive(from.getIsActive());
        to.setRemarks(from.getRemarks());
        to.setZxDept(from.getZxDept());
        to.setZxDesg(from.getZxDesg());
        to.setZxLookupBloodGroup(from.getZxLookupBloodGroup());
        to.setSlNo(from.getSlNo());
        to.setPhoneNumbers(from.getPhoneNumbers());
        to.setAttributes(from.getAttributes());
        to.setVacationBookings(from.getVacationBookings());
        to.setNickNames(from.getNickNames());
        to.setZxChooses(from.getZxChooses());
        to.setZxEmpEduDtls(from.getZxEmpEduDtls());
        to.setZxEmpTrainingDtls(from.getZxEmpTrainingDtls());

    }

    @Override
    @Transactional
    public ZxEmp findById(BigInteger id) throws ZxEmpNotFoundException {
        ZxEmp zxEmp = zxEmpRepository.findOne(id);
        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }
        Hibernate.initialize(zxEmp.getPhoneNumbers());
        Hibernate.initialize(zxEmp.getAttributes());
        Hibernate.initialize(zxEmp.getVacationBookings());
        Hibernate.initialize(zxEmp.getNickNames());
        Hibernate.initialize(zxEmp.getZxEmpEduDtls());
        Hibernate.initialize(zxEmp.getZxEmpTrainingDtls());

        return zxEmp;
    }

    @Override
    @Transactional
    public Iterable<ZxEmp> findAll() {
        Iterable<ZxEmp> zxEmps = zxEmpRepository.findAll();

        for (ZxEmp zxEmp : zxEmps) {
            Hibernate.initialize(zxEmp.getPhoneNumbers());
            Hibernate.initialize(zxEmp.getAttributes());
            Hibernate.initialize(zxEmp.getVacationBookings());
            Hibernate.initialize(zxEmp.getNickNames());
            Hibernate.initialize(zxEmp.getZxEmpEduDtls());
            Hibernate.initialize(zxEmp.getZxEmpTrainingDtls());

        }

        return zxEmps;
    }

    @Transactional
    @Override
    public Iterable<ZxEmp> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxEmp> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = ZxEmpNotFoundException.class)
    public ZxEmp delete(BigInteger id) throws ZxEmpNotFoundException {

        ZxEmp zxEmp = zxEmpRepository.findOne(id);

        if (zxEmp == null) {
            throw new ZxEmpNotFoundException();
        }
        zxEmpRepository.delete(id);
        return zxEmp;
    }
}