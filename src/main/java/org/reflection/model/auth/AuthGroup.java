package org.reflection.model.auth;

import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.Abstract;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "AUTH_GROUP")
public class AuthGroup extends Abstract {

    @Column(name = "AUTHORITY", length = 50, unique = true, nullable = false)
    private String authority;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_GROUP_AUTH_ROLE",
            joinColumns = {
                    @JoinColumn(name = "AUTH_GROUP_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "AUTH_ROLE_ID")})
    private Set<AuthRole> authRoles = new LinkedHashSet<>();

    public AuthGroup() {
        this("ADMIN");
    }

    public AuthGroup(String authority) {
        this.authority = authority;
    }

    public Set<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Set<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }

}
