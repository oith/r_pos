/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reflection.service.auth;

import org.reflection.model.auth.AuthUser;

public interface SpringSecurityService {

    public AuthUser currAuthUser();

    public void autoLogin(String username, String password);
}
