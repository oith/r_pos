package org.reflection.model.auth;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacPassword;
import com.oith.annotation.MacSearchable;
import org.hibernate.validator.constraints.Email;
import org.reflection.model.com.Abstract;
import org.reflection.model.com.enums.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "AUTH_USER")
@MacCodable(id = "id", code = "username", caption = "fullName")
public class AuthUser extends Abstract {

    @MacSearchable
    @Column(name = "USERNAME", length = 30, unique = true, nullable = false)
    private String username;

    @MacPassword
    @Column(name = "PASSWORD", length = 128, nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @MacSearchable
    @Column(name = "DISPLAY_NAME", length = 10, nullable = false)
    private String displayName;

    @MacSearchable
    @Column(name = "CELL_NO", unique = true, length = 20)
    private String cellNo;
    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE", length = 10)
    private Title title;
    @Column(name = "FULL_NAME", length = 50, nullable = false)
    private String fullName;
    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 10, nullable = false)
    private Gender gender;
    @Column(name = "DOB", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    private Date dob;
    @Column(name = "DOJ")
    @Temporal(TemporalType.DATE)
    @Past
    private Date doj;
    @Email
    @Column(name = "EMAIL", unique = true, length = 30)
    private String email;
    @MacImagable
    @Column(name = "PIC")
    private String pic;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG", length = 10, nullable = false)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOCALE", length = 10, nullable = false)
    private Locale locale = Locale.bn_BD;

    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY", length = 10, nullable = false)
    private Country country = Country.BD;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", length = 10, nullable = false)
    private Currency currency = Currency.BDT;

    @Enumerated(EnumType.STRING)
    @Column(name = "MENU_ORIENTATION", length = 10, nullable = false)
    private MenuOrientation menuOrientation = MenuOrientation.MENU_LEFT;


    @Column(name = "ENABLED")
    private Boolean enabled = Boolean.TRUE;
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired = Boolean.TRUE;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked = Boolean.TRUE;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired = Boolean.TRUE;
    @Column(name = "OPEN_IN_NEW_PAGE")
    private Boolean openInNewPage = Boolean.TRUE;

    @Column(name = "PARAMS", length = 2000)
    private String params;
    @Column(name = "FAVORITE", length = 2000)
    private String favorite;

    @Column(name = "DEPARTMENT", length = 50)
    private String department;

    @Column(name = "AFTER_LOGIN_URL", length = 100)
    private String afterLoginUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USER_AUTH_ROLE",
            joinColumns = {
                    @JoinColumn(name = "AUTH_USER_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "AUTH_ROLE_ID")})
    private Set<AuthRole> authRoles = new LinkedHashSet();

    public AuthUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }


    public Boolean getOpenInNewPage() {
        return openInNewPage;
    }

    public void setOpenInNewPage(Boolean openInNewPage) {
        this.openInNewPage = openInNewPage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public MenuOrientation getMenuOrientation() {
        return menuOrientation;
    }

    public void setMenuOrientation(MenuOrientation menuOrientation) {
        this.menuOrientation = menuOrientation;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getAfterLoginUrl() {
        return afterLoginUrl;
    }

    public void setAfterLoginUrl(String afterLoginUrl) {
        this.afterLoginUrl = afterLoginUrl;
    }

    public Set<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Set<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
