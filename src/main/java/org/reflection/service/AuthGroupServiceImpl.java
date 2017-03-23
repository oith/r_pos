package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthGroupNotFoundException;
import org.reflection.model.auth.AuthGroup;
import org.reflection.repositories.AuthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("authGroupService")
@Transactional(readOnly = true)
public class AuthGroupServiceImpl implements AuthGroupService {

    @Autowired
    private AuthGroupRepository authGroupRepository;


    @Transactional
    @Override
    public AuthGroup create(AuthGroup lookup) {
        return authGroupRepository.save(lookup);
    }

    @Transactional(rollbackFor = AuthGroupNotFoundException.class)
    @Override
    public AuthGroup update(AuthGroup updated) throws AuthGroupNotFoundException {
        AuthGroup authGroup = authGroupRepository.findOne(updated.getId());
        if (authGroup == null) {
            throw new AuthGroupNotFoundException();
        }
        //BeanUtils.copyProperties(updated, authGroup);


        copyProperties(updated, authGroup);

        return authGroupRepository.save(authGroup);
    }

    @Transactional(rollbackFor = AuthGroupNotFoundException.class)
    @Override
    public AuthGroup copy(final AuthGroup copied) {

        AuthGroup authGroupOrginal = authGroupRepository.findOne(copied.getId());

        AuthGroup authGroup = new AuthGroup();
        //BeanUtils.copyProperties(copied, authGroup);
        //authGroup.setId(null);


        copyProperties(copied, authGroup);


        return authGroupRepository.save(authGroup);
    }

    private void copyProperties(AuthGroup from, AuthGroup to) {
        to.setAuthority(from.getAuthority());
        to.setIsActive(from.getIsActive());
        to.setAuthRoles(from.getAuthRoles());

    }

    @Override
    @Transactional
    public AuthGroup findById(BigInteger id) throws AuthGroupNotFoundException {
        AuthGroup authGroup = authGroupRepository.findOne(id);
        if (authGroup == null) {
            throw new AuthGroupNotFoundException();
        }

        return authGroup;
    }

    @Override
    @Transactional
    public Iterable<AuthGroup> findAll() {
        Iterable<AuthGroup> authGroups = authGroupRepository.findAll();

        for (AuthGroup authGroup : authGroups) {

        }

        return authGroups;
    }

    @Transactional
    @Override
    public Iterable<AuthGroup> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthGroup> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthGroupNotFoundException.class)
    public AuthGroup delete(BigInteger id) throws AuthGroupNotFoundException {

        AuthGroup authGroup = authGroupRepository.findOne(id);

        if (authGroup == null) {
            throw new AuthGroupNotFoundException();
        }
        authGroupRepository.delete(id);
        return authGroup;
    }
}