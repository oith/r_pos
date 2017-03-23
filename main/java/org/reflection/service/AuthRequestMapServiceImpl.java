package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRequestMapNotFoundException;
import org.reflection.model.auth.AuthRequestMap;
import org.reflection.repositories.AuthRequestMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("authRequestMapService")
@Transactional(readOnly = true)
public class AuthRequestMapServiceImpl implements AuthRequestMapService {

    @Autowired
    private AuthRequestMapRepository authRequestMapRepository;


    @Transactional
    @Override
    public AuthRequestMap create(AuthRequestMap lookup) {
        return authRequestMapRepository.save(lookup);
    }

    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap update(AuthRequestMap updated) throws AuthRequestMapNotFoundException {
        AuthRequestMap authRequestMap = authRequestMapRepository.findOne(updated.getId());
        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }
        //BeanUtils.copyProperties(updated, authRequestMap);


        copyProperties(updated, authRequestMap);

        return authRequestMapRepository.save(authRequestMap);
    }

    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap copy(final AuthRequestMap copied) {

        AuthRequestMap authRequestMapOrginal = authRequestMapRepository.findOne(copied.getId());

        AuthRequestMap authRequestMap = new AuthRequestMap();
        //BeanUtils.copyProperties(copied, authRequestMap);
        //authRequestMap.setId(null);


        copyProperties(copied, authRequestMap);


        return authRequestMapRepository.save(authRequestMap);
    }

    private void copyProperties(AuthRequestMap from, AuthRequestMap to) {
        to.setConfigAttribute(from.getConfigAttribute());
        to.setHttpMethod(from.getHttpMethod());
        to.setUrl(from.getUrl());

    }

    @Override
    @Transactional
    public AuthRequestMap findById(BigInteger id) throws AuthRequestMapNotFoundException {
        AuthRequestMap authRequestMap = authRequestMapRepository.findOne(id);
        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }

        return authRequestMap;
    }

    @Override
    @Transactional
    public Iterable<AuthRequestMap> findAll() {
        Iterable<AuthRequestMap> authRequestMaps = authRequestMapRepository.findAll();

        for (AuthRequestMap authRequestMap : authRequestMaps) {

        }

        return authRequestMaps;
    }

    @Transactional
    @Override
    public Iterable<AuthRequestMap> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthRequestMap> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    public AuthRequestMap delete(BigInteger id) throws AuthRequestMapNotFoundException {

        AuthRequestMap authRequestMap = authRequestMapRepository.findOne(id);

        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }
        authRequestMapRepository.delete(id);
        return authRequestMap;
    }
}