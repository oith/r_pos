package org.reflection.model.auth;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EmbdAuthUserAuthRolePK implements Serializable {

    @ManyToOne
    private AuthUser authUser;
    @ManyToOne
    private AuthRole authRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmbdAuthUserAuthRolePK)) return false;

        EmbdAuthUserAuthRolePK that = (EmbdAuthUserAuthRolePK) o;

        if (!getAuthUser().equals(that.getAuthUser())) return false;
        return getAuthRole().equals(that.getAuthRole());

    }

    @Override
    public int hashCode() {
        int result = getAuthUser().hashCode();
        result = 31 * result + getAuthRole().hashCode();
        return result;
    }

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

    @Override
    public String toString() {
        return authRole.toString();
    }

}
