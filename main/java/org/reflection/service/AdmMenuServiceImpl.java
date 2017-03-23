package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuNotFoundException;
import org.reflection.model.com.AdmMenu;
import org.reflection.model.com.AdmMenuItem;
import org.reflection.repositories.AdmMenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admMenuService")
@Transactional(readOnly = true)
public class AdmMenuServiceImpl implements AdmMenuService {

    @Autowired
    private AdmMenuRepository admMenuRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmMenu findByCode(String code) throws AdmMenuNotFoundException {
        return admMenuRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmMenu create(AdmMenu lookup) {
        return admMenuRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmMenuNotFoundException.class)
    @Override
    public AdmMenu update(AdmMenu updated) throws AdmMenuNotFoundException {
        AdmMenu admMenu = admMenuRepository.findOne(updated.getId());
        if (admMenu == null) {
            throw new AdmMenuNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admMenu);


        copyProperties(updated, admMenu);

        return admMenuRepository.save(admMenu);
    }

    @Transactional(rollbackFor = AdmMenuNotFoundException.class)
    @Override
    public AdmMenu copy(final AdmMenu copied) {

        AdmMenu admMenuOrginal = admMenuRepository.findOne(copied.getId());

        AdmMenu admMenu = new AdmMenu();
        //BeanUtils.copyProperties(copied, admMenu);
        //admMenu.setId(null);


        copyProperties(copied, admMenu);

        for (AdmMenuItem currDet : admMenuOrginal.getAdmMenuItems()) {

            AdmMenuItem det = new AdmMenuItem();
            BeanUtils.copyProperties(currDet, det);
            det.setId(null);

            det.setAdmMenu(admMenu);
            if (admMenu.getAdmMenuItems() == null) {
                admMenu.setAdmMenuItems(new java.util.LinkedHashSet());
            }
            admMenu.getAdmMenuItems().add(det);
        }


        return admMenuRepository.save(admMenu);
    }

    private void copyProperties(AdmMenu from, AdmMenu to) {
        to.setCode(from.getCode());
        to.setFullName(from.getFullName());
        to.setFullNameNative(from.getFullNameNative());
        to.setIsActive(from.getIsActive());
        to.setSlNo(from.getSlNo());
        to.setDescription(from.getDescription());
        to.setTooltip(from.getTooltip());
        to.setDisplayIconClass(from.getDisplayIconClass());
        to.setIsExternal(from.getIsExternal());
        to.setIsOpenInNewTab(from.getIsOpenInNewTab());
        to.setAdmSubModule(from.getAdmSubModule());
        to.setAdmMenuItems(from.getAdmMenuItems());
        to.setUrlPath(from.getUrlPath());

    }

    @Override
    @Transactional
    public AdmMenu findById(BigInteger id) throws AdmMenuNotFoundException {
        AdmMenu admMenu = admMenuRepository.findOne(id);
        if (admMenu == null) {
            throw new AdmMenuNotFoundException();
        }
        Hibernate.initialize(admMenu.getAdmMenuItems());

        return admMenu;
    }

    @Override
    @Transactional
    public Iterable<AdmMenu> findAll() {
        Iterable<AdmMenu> admMenus = admMenuRepository.findAll();

        for (AdmMenu admMenu : admMenus) {
            Hibernate.initialize(admMenu.getAdmMenuItems());

        }

        return admMenus;
    }

    @Transactional
    @Override
    public Iterable<AdmMenu> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmMenu> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmMenuNotFoundException.class)
    public AdmMenu delete(BigInteger id) throws AdmMenuNotFoundException {

        AdmMenu admMenu = admMenuRepository.findOne(id);

        if (admMenu == null) {
            throw new AdmMenuNotFoundException();
        }
        admMenuRepository.delete(id);
        return admMenu;
    }
}