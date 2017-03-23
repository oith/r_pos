package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;

@Entity
@Table(name = "ADM_MENU_COMMON")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmMenuCommon extends AbstractAdm implements IDetail {

    @Column(name = "TOOLTIP", length = 30)
    private String tooltip;
    @Column(name = "DISPLAY_ICON_CLASS", length = 100)
    private String displayIconClass = "fa fa-car";
    @Column(name = "IS_EXTERNAL", nullable = false)
    private Boolean isExternal;
    @Column(name = "IS_OPEN_IN_NEW_TAB", nullable = false)
    private Boolean isOpenInNewTab;

    @JoinColumn(name = "ADM_SUB_MODULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmSubModule admSubModule;

    @Transient
    private CrudType crudType;

    public AdmMenuCommon() {
    }

    public AdmMenuCommon(String code, String fullName) {
        super(code, fullName);
    }

    public AdmMenuCommon(String code, String fullName, String displayIconClass, String tooltip, Boolean isExternal, Boolean isOpenInNewTab) {
        super(code, fullName);
        this.displayIconClass = displayIconClass;
        this.tooltip = tooltip;
        this.isExternal = isExternal;
        this.isOpenInNewTab = isOpenInNewTab;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getDisplayIconClass() {
        return displayIconClass;
    }

    public void setDisplayIconClass(String displayIconClass) {
        this.displayIconClass = displayIconClass;
    }

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    public Boolean getIsOpenInNewTab() {
        return isOpenInNewTab;
    }

    public void setIsOpenInNewTab(Boolean isOpenInNewTab) {
        this.isOpenInNewTab = isOpenInNewTab;
    }

    public AdmSubModule getAdmSubModule() {
        return admSubModule;
    }

    public void setAdmSubModule(AdmSubModule admSubModule) {
        this.admSubModule = admSubModule;
    }

    @Override
    public CrudType getCrudType() {
        return crudType;
    }

    @Override
    public void setCrudType(CrudType crudType) {
        this.crudType = crudType;
    }

    @Override
    public String toString() {
        return getCode() + "-" + getFullName();
    }

}
