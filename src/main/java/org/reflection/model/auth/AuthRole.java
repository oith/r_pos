package org.reflection.model.auth;

import org.reflection.model.com.Abstract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AUTH_ROLE")
@XmlRootElement
public class AuthRole extends Abstract {

    @Column(name = "AUTHORITY", length = 30, unique = true, nullable = false)
    private String authority;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;

//    @MacCareless
//    @ManyToMany(mappedBy = "authRoles")
//    private Set<AuthUser> authUsers = new HashSet();

    public AuthRole() {
    }

    public AuthRole(String authority) {
        this.authority = authority;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
