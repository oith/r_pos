package org.reflection.service.auth;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.model.auth.AuthRole;
import org.reflection.repositories.AuthRoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("authRoleService")
@Transactional(readOnly = true)
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;


    @Transactional
    @Override
    public AuthRole create(AuthRole lookup) {
        return authRoleRepository.save(lookup);
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole update(AuthRole updated) throws AuthRoleNotFoundException {
        AuthRole authRole = authRoleRepository.findOne(updated.getId());
        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }
        BeanUtils.copyProperties(updated, authRole);
        return authRoleRepository.save(authRole);
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole copy(final AuthRole copied) {

        AuthRole authRoleOrginal = authRoleRepository.findOne(copied.getId());

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(copied, authRole);
        authRole.setId(null);
        

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
        return authRoleRepository.save(authRole);
    }

    @Override
    @Transactional
    public AuthRole findById(BigInteger id) throws AuthRoleNotFoundException {
        AuthRole authRole = authRoleRepository.findOne(id);
        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }

        return authRole;
    }

    @Override
    @Transactional
    public Iterable<AuthRole> findAll() {
        Iterable<AuthRole> authRoles = authRoleRepository.findAll();

        for (AuthRole authRole : authRoles) {

        }

        return authRoles;
    }

    @Transactional
    @Override
    public Iterable<AuthRole> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthRole> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    public AuthRole delete(BigInteger id) throws AuthRoleNotFoundException {

        AuthRole authRole = authRoleRepository.findOne(id);

        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }
        authRoleRepository.delete(id);
        return authRole;
    }
}