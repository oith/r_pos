package org.reflection.model.auth;

import com.oith.annotation.MacCareless;
import org.reflection.model.com.enums.RoleIncludeExclude;

import javax.persistence.*;
import java.io.Serializable;

@MacCareless
@Entity
@Table(name = "AUTH_USER_AUTH_ROLE")
@AssociationOverrides({
        @AssociationOverride(name = "id.authUser", joinColumns = @JoinColumn(name = "AUTH_USER_ID")),
        @AssociationOverride(name = "id.authRole", joinColumns = @JoinColumn(name = "AUTH_ROLE_ID"))})
public class AuthUserAuthRole implements Serializable {

    @EmbeddedId
    private EmbdAuthUserAuthRolePK id = new EmbdAuthUserAuthRolePK();

    @Enumerated(EnumType.STRING)
    @Column(name = "INCLUDE_EXCLUDE", length = 10, nullable = false)
    private RoleIncludeExclude emIncludeExclude;

    public AuthUserAuthRole() {
    }

    public AuthUserAuthRole(AuthUser authUser, AuthRole authRole, RoleIncludeExclude emIncludeExclude) {
        setAuthUser(authUser);
        setAuthRole(authRole);
        this.emIncludeExclude = emIncludeExclude;
    }

    public EmbdAuthUserAuthRolePK getId() {
        return id;
    }

    public void setId(EmbdAuthUserAuthRolePK id) {
        this.id = id;
    }

    public AuthUser getAuthUser() {
        return getId().getAuthUser();
    }

    public void setAuthUser(AuthUser authUser) {
        getId().setAuthUser(authUser);
    }

    public AuthRole getAuthRole() {
        return getId().getAuthRole();
    }

    public void setAuthRole(AuthRole authRole) {
        getId().setAuthRole(authRole);
    }

    public RoleIncludeExclude getEmIncludeExclude() {
        return emIncludeExclude;
    }

    public void setEmIncludeExclude(RoleIncludeExclude emIncludeExclude) {
        this.emIncludeExclude = emIncludeExclude;
    }

    @Override
    public String toString() {
        return "AuthUserAuthRole{" + "id=" + id + ", answer=" + emIncludeExclude + '}';
    }
}
