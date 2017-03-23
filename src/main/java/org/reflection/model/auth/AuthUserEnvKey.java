package org.reflection.model.auth;

import org.reflection.model.com.Abstract;
import org.reflection.model.com.enums.EnvKey;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_ENV_KEY",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"AUTH_USER_ID", "ENV_KEY"})
        })
public class AuthUserEnvKey extends Abstract {

    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTH_USER_ID", nullable = false)
    private AuthUser authUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENV_KEY", length = 30, nullable = false)
    private EnvKey envKey;

    @Column(name = "ENV_VALUE", length = 2000, nullable = false)
    private String envValue;

    public AuthUserEnvKey() {
    }

    public AuthUserEnvKey(AuthUser authUser, EnvKey envKey, String envValue) {
        this.authUser = authUser;
        this.envKey = envKey;
        this.envValue = envValue;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getEnvValue() {
        return envValue;
    }

    public void setEnvValue(String envValue) {
        this.envValue = envValue;
    }

    @Override
    public String toString() {
        return "AuthUserEnvKey{" + "authUser=" + authUser + ", envKey=" + envKey + ", envValue=" + envValue + '}';
    }

    public EnvKey getEnvKey() {
        return envKey;
    }

    public void setEnvKey(EnvKey envKey) {
        this.envKey = envKey;
    }

}
