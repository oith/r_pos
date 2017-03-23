package org.reflection.service.auth;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Hibernate;
import org.reflection.model.auth.AuthUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mbadiuzzaman on 6/3/2017.
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            AuthUser authUser = authUserService.findByUsername(username);

            Hibernate.initialize(authUser.getAuthGroups());
            Hibernate.initialize(authUser.getAuthUserAuthQuestions());
            Hibernate.initialize(authUser.getAuthUserAuthRoles());
            Hibernate.initialize(authUser.getAuthUserEnvKeys());

            Set<GrantedAuthority> authorities = new LinkedHashSet<>();

            for (org.reflection.model.auth.AuthGroup authGroup : authUser.getAuthGroups()) {
                for (org.reflection.model.auth.AuthRole authRole : authGroup.getAuthRoles()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getAuthority()));
                }
            }
//        for (org.reflection.model.security.AuthRole authRole : user.getAuthRoles()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getAuthority()));
//        }

            AuthUserExt authUserExt
                    = new AuthUserExt(authUser.getUsername(), authUser.getPassword(), authorities);

            String aaa = ToStringBuilder.reflectionToString(authUser);
            String aaa1 = ToStringBuilder.reflectionToString(authUserExt);
            BeanUtils.copyProperties(authUser, authUserExt);
            String bbb = ToStringBuilder.reflectionToString(authUser);
            String bbb1 = ToStringBuilder.reflectionToString(authUserExt);

            System.out.println("oo aaa" + aaa);
            System.out.println("oo aaa1" + aaa1);
            System.out.println("oo bbb" + bbb);
            System.out.println("oo bbb1" + bbb1);

            return authUserExt;
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
    }
}
