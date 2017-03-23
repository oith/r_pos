package org.reflection.service.util;

import org.reflection.model.auth.AuthUser;
import org.reflection.service.auth.AuthUserExt;
import org.reflection.service.auth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private AuthUserService authUserService;

    @Override
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {

        if (currentPassword.trim().isEmpty()
                || newPassword.trim().isEmpty()
                || confirmPassword.trim().isEmpty()) {
            throw new Exception("Empty Field");
        }
        if (newPassword.length() != confirmPassword.length()) {
            throw new Exception("New Password length not equal to Confirm Password length.");
        }
        if (currentPassword.equals(newPassword)) {
            throw new Exception("Same password.");
        }

        AuthUserExt authUserExt = (AuthUserExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentPasswordEnc = passwordEncoder.encode(currentPassword);

        if (!currentPasswordEnc.equals(authUserExt.getPassword())) {
            throw new Exception("current password not match.");
        }

        AuthUser authUser = authUserService.findByUsername(authUserExt.getUsername());
        authUser.setPassword(passwordEncoder.encode(newPassword));
    }

    @Override
    public AuthUser currentAuthUser() {
        try {
            AuthUserExt authUserExt = (AuthUserExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return authUserService.findByUsername(authUserExt.getUsername());
        } catch (Exception e) {
            return null;
        }
    }
}
