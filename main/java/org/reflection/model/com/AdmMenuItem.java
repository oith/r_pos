package org.reflection.model.com;

import com.oith.annotation.MacCodable;

import javax.persistence.*;

@Entity
@Table(name = "ADM_MENU_ITEM")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmMenuItem extends AdmMenuCommon implements IDetail {

    @JoinColumn(name = "ADM_MENU_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmMenu admMenu;

    @Column(name = "URL_PATH", unique = true, length = 100, nullable = false)
    private String urlPath;

    public AdmMenuItem() {
    }

    public AdmMenuItem(String code, String fullName) {
        super(code, fullName);
    }

    public AdmMenuItem(String code, String fullName, AdmMenu admMenu, Boolean isExternal, Boolean isOpenInNewTab, String urlPath, String displayIconClass) {
        super(code, fullName, displayIconClass, code, isExternal, isOpenInNewTab);
        this.admMenu = admMenu;
        this.urlPath = urlPath;
    }

    public AdmMenu getAdmMenu() {
        return admMenu;
    }

    public void setAdmMenu(AdmMenu admMenu) {
        this.admMenu = admMenu;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

}
