package org.reflection.service.auth;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.model.auth.AuthUser;
import org.reflection.model.auth.AuthUserAuthRole;
import org.reflection.repositories.AuthUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;

@Service("authUserService")
@Transactional(readOnly = true)
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public AuthUser findByUsername(String username) throws AuthUserNotFoundException {
        return authUserRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public AuthUser create(AuthUser authUser) {


        authUser.setPassword(bCryptPasswordEncoder.encode(authUser.getPassword()));


        return authUserRepository.save(authUser);
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    @Override
    public AuthUser update(AuthUser updated) throws AuthUserNotFoundException {
        AuthUser authUser = authUserRepository.findOne(updated.getId());
        if (authUser == null) {
            throw new AuthUserNotFoundException();
        }
        BeanUtils.copyProperties(updated, authUser);
        return authUserRepository.save(authUser);
    }

    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    @Override
    public AuthUser copy(final AuthUser copied) {

        AuthUser authUserOrginal = authUserRepository.findOne(copied.getId());

        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(copied, authUser);
        authUser.setId(null);

        for (AuthUserAuthRole currDet : authUserOrginal.getAuthUserAuthRoles()) {

            AuthUserAuthRole det = new AuthUserAuthRole();
            BeanUtils.copyProperties(currDet, det);

            det.setAuthUser(authUser);
            if (authUser.getAuthUserAuthRoles() == null) {
                authUser.setAuthUserAuthRoles(new java.util.LinkedHashSet());
            }
            authUser.getAuthUserAuthRoles().add(det);
        }

/*
//needs to repeat for all details
        for (ZxEmpEduDtl object : zxEmpOrginal.getZxEmpEduDtls()) {

            ZxEmpEduDtl zxEmpd = new ZxEmpEduDtl();
            BeanUtils.copyProperties(object, zxEmpd);
            zxEmpd.setId(null);

            zxEmpd.setZxEmp(zxEmp);
            if (zxEmp.getZxEmpEduDtls() == null) {
                zxEmp.setZxEmpEduDtls(new ArrayList());
            }
            zxEmp.getZxEmpEduDtls().add(zxEmpd);
        }
*/
        return authUserRepository.save(authUser);
    }

    @Override
    @Transactional
    public AuthUser findById(BigInteger id) throws AuthUserNotFoundException {
        AuthUser authUser = authUserRepository.findOne(id);
        if (authUser == null) {
            throw new AuthUserNotFoundException();
        }

        Hibernate.initialize(authUser.getAuthUserAuthRoles());

        return authUser;
    }

    @Override
    @Transactional
    public Iterable<AuthUser> findAll() {
        Iterable<AuthUser> authUsers = authUserRepository.findAll();

        for (AuthUser authUser : authUsers) {
            Hibernate.initialize(authUser.getAuthUserAuthRoles());
        }

        return authUsers;
    }

    @Transactional
    @Override
    public Iterable<AuthUser> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthUser> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthUserNotFoundException.class)
    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException {

        AuthUser authUser = authUserRepository.findOne(id);

        if (authUser == null) {
            throw new AuthUserNotFoundException();
        }
        authUserRepository.delete(id);
        return authUser;
    }
}