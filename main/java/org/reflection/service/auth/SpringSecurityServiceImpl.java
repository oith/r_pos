package org.reflection.service.auth;

import org.reflection.model.auth.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("springSecurityService")
@Transactional(readOnly = true)
public class SpringSecurityServiceImpl implements SpringSecurityService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthUser currAuthUser() {
        try {
            AuthUserExt authUserExt = (AuthUserExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return authUserService.findByUsername(authUserExt.getUsername());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println(String.format("Auto login %s successfully!", username));
        }
    }
}
