package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuItemNotFoundException;
import org.reflection.model.com.AdmMenuItem;
import org.reflection.repositories.AdmMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("admMenuItemService")
@Transactional(readOnly = true)
public class AdmMenuItemServiceImpl implements AdmMenuItemService {

    @Autowired
    private AdmMenuItemRepository admMenuItemRepository;

    @Transactional(readOnly = true)
    @Override
    public AdmMenuItem findByCode(String code) throws AdmMenuItemNotFoundException {
        return admMenuItemRepository.findByCode(code);
    }

    @Transactional
    @Override
    public AdmMenuItem create(AdmMenuItem lookup) {
        return admMenuItemRepository.save(lookup);
    }

    @Transactional(rollbackFor = AdmMenuItemNotFoundException.class)
    @Override
    public AdmMenuItem update(AdmMenuItem updated) throws AdmMenuItemNotFoundException {
        AdmMenuItem admMenuItem = admMenuItemRepository.findOne(updated.getId());
        if (admMenuItem == null) {
            throw new AdmMenuItemNotFoundException();
        }
        //BeanUtils.copyProperties(updated, admMenuItem);


        copyProperties(updated, admMenuItem);

        return admMenuItemRepository.save(admMenuItem);
    }

    @Transactional(rollbackFor = AdmMenuItemNotFoundException.class)
    @Override
    public AdmMenuItem copy(final AdmMenuItem copied) {

        AdmMenuItem admMenuItemOrginal = admMenuItemRepository.findOne(copied.getId());

        AdmMenuItem admMenuItem = new AdmMenuItem();
        //BeanUtils.copyProperties(copied, admMenuItem);
        //admMenuItem.setId(null);


        copyProperties(copied, admMenuItem);


        return admMenuItemRepository.save(admMenuItem);
    }

    private void copyProperties(AdmMenuItem from, AdmMenuItem to) {
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
        to.setAdmMenu(from.getAdmMenu());
        to.setUrlPath(from.getUrlPath());

    }

    @Override
    @Transactional
    public AdmMenuItem findById(BigInteger id) throws AdmMenuItemNotFoundException {
        AdmMenuItem admMenuItem = admMenuItemRepository.findOne(id);
        if (admMenuItem == null) {
            throw new AdmMenuItemNotFoundException();
        }

        return admMenuItem;
    }

    @Override
    @Transactional
    public Iterable<AdmMenuItem> findAll() {
        Iterable<AdmMenuItem> admMenuItems = admMenuItemRepository.findAll();

        for (AdmMenuItem admMenuItem : admMenuItems) {

        }

        return admMenuItems;
    }

    @Transactional
    @Override
    public Iterable<AdmMenuItem> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmMenuItem> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AdmMenuItemNotFoundException.class)
    public AdmMenuItem delete(BigInteger id) throws AdmMenuItemNotFoundException {

        AdmMenuItem admMenuItem = admMenuItemRepository.findOne(id);

        if (admMenuItem == null) {
            throw new AdmMenuItemNotFoundException();
        }
        admMenuItemRepository.delete(id);
        return admMenuItem;
    }
}