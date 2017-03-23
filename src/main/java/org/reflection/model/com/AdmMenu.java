package org.reflection.model.com;

import com.oith.annotation.MacCodable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ADM_MENU")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmMenu extends AdmMenuCommon {


    @OneToMany(mappedBy = "admMenu")
    private Set<AdmMenuItem> admMenuItems = new LinkedHashSet();
    @Column(name = "URL_PATH", length = 100)
    private String urlPath;

    public AdmMenu() {
    }

    public AdmMenu(String code, String fullName) {
        super(code, fullName);
    }

    public Set<AdmMenuItem> getAdmMenuItems() {
        return admMenuItems;
    }

    public void setAdmMenuItems(Set<AdmMenuItem> admMenuItems) {
        this.admMenuItems = admMenuItems;
    }


    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

}
