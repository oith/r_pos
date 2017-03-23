package org.reflection.service.util;

import org.reflection.model.auth.AuthUser;

public interface ProfileService {

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception;

    public AuthUser currentAuthUser();

}
