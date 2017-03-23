package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserEnvKeyNotFoundException;
import org.reflection.model.auth.AuthUserEnvKey;
import org.reflection.repositories.AuthUserEnvKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("authUserEnvKeyService")
@Transactional(readOnly = true)
public class AuthUserEnvKeyServiceImpl implements AuthUserEnvKeyService {

    @Autowired
    private AuthUserEnvKeyRepository authUserEnvKeyRepository;


    @Transactional
    @Override
    public AuthUserEnvKey create(AuthUserEnvKey lookup) {
        return authUserEnvKeyRepository.save(lookup);
    }

    @Transactional(rollbackFor = AuthUserEnvKeyNotFoundException.class)
    @Override
    public AuthUserEnvKey update(AuthUserEnvKey updated) throws AuthUserEnvKeyNotFoundException {
        AuthUserEnvKey authUserEnvKey = authUserEnvKeyRepository.findOne(updated.getId());
        if (authUserEnvKey == null) {
            throw new AuthUserEnvKeyNotFoundException();
        }
        //BeanUtils.copyProperties(updated, authUserEnvKey);


        copyProperties(updated, authUserEnvKey);

        return authUserEnvKeyRepository.save(authUserEnvKey);
    }

    @Transactional(rollbackFor = AuthUserEnvKeyNotFoundException.class)
    @Override
    public AuthUserEnvKey copy(final AuthUserEnvKey copied) {

        AuthUserEnvKey authUserEnvKeyOrginal = authUserEnvKeyRepository.findOne(copied.getId());

        AuthUserEnvKey authUserEnvKey = new AuthUserEnvKey();
        //BeanUtils.copyProperties(copied, authUserEnvKey);
        //authUserEnvKey.setId(null);


        copyProperties(copied, authUserEnvKey);


        return authUserEnvKeyRepository.save(authUserEnvKey);
    }

    private void copyProperties(AuthUserEnvKey from, AuthUserEnvKey to) {
        to.setAuthUser(from.getAuthUser());
        to.setEnvKey(from.getEnvKey());
        to.setEnvValue(from.getEnvValue());

    }

    @Override
    @Transactional
    public AuthUserEnvKey findById(BigInteger id) throws AuthUserEnvKeyNotFoundException {
        AuthUserEnvKey authUserEnvKey = authUserEnvKeyRepository.findOne(id);
        if (authUserEnvKey == null) {
            throw new AuthUserEnvKeyNotFoundException();
        }

        return authUserEnvKey;
    }

    @Override
    @Transactional
    public Iterable<AuthUserEnvKey> findAll() {
        Iterable<AuthUserEnvKey> authUserEnvKeys = authUserEnvKeyRepository.findAll();

        for (AuthUserEnvKey authUserEnvKey : authUserEnvKeys) {

        }

        return authUserEnvKeys;
    }

    @Transactional
    @Override
    public Iterable<AuthUserEnvKey> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthUserEnvKey> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthUserEnvKeyNotFoundException.class)
    public AuthUserEnvKey delete(BigInteger id) throws AuthUserEnvKeyNotFoundException {

        AuthUserEnvKey authUserEnvKey = authUserEnvKeyRepository.findOne(id);

        if (authUserEnvKey == null) {
            throw new AuthUserEnvKeyNotFoundException();
        }
        authUserEnvKeyRepository.delete(id);
        return authUserEnvKey;
    }
}