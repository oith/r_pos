package org.reflection.service.auth;

import org.reflection.model.com.enums.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.Set;

public class AuthUserExt extends User {

    private String displayName;
    private String fullName;
    private Gender gender;
    private Date dob;
    private Date doj;
    private String email;
    private String pic;
    private String completeMenu;
    private String params;
    private String favorite;
    private String afterLoginUrl;
    private Language language;
    private Country country;
    private Locale locale;
    private MenuOrientation menuOrientation;

    public AuthUserExt(String username, String password, Set<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCompleteMenu() {
        return completeMenu;
    }

    public void setCompleteMenu(String completeMenu) {
        this.completeMenu = completeMenu;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public MenuOrientation getMenuOrientation() {
        return menuOrientation;
    }

    public void setMenuOrientation(MenuOrientation menuOrientation) {
        this.menuOrientation = menuOrientation;
    }

    public String getAfterLoginUrl() {
        return afterLoginUrl;
    }

    public void setAfterLoginUrl(String afterLoginUrl) {
        this.afterLoginUrl = afterLoginUrl;
    }

    @Override
    public String toString() {
        return fullName;
    }

}
