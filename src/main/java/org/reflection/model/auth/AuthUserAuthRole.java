package org.reflection.model.auth;

import org.reflection.model.com.Abstract;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_AUTH_ROLE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"AUTH_USER_ID", "AUTH_ROLE_ID"}))
public class AuthUserAuthRole extends Abstract {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTH_USER_ID", nullable = false)
    private AuthUser authUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTH_ROLE_ID", nullable = false)
    private AuthRole authRole;

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public AuthRole getAuthRole() {
        return authRole;
    }

    public void setAuthRole(AuthRole authRole) {
        this.authRole = authRole;
    }
}
