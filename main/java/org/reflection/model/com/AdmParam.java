package org.reflection.model.com;

import org.reflection.model.com.enums.WidgetType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADM_PARAM")
public class AdmParam extends AbstractLookable {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "WIDGET_TYPE", length = 30, nullable = false)
    private WidgetType widgetType;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "SL_NO")
    private Byte slNo;
    @Column(name = "CMD", length = 500)
    private String cmd;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT")
    private String helpText;

    public AdmParam() {
    }

    public AdmParam(String code, String fullName, WidgetType widgetType, Boolean isActive, Boolean isMandatory, Byte slNo, String cmd, String defaultVal, String helpText) {
        super(code, fullName);
        this.widgetType = widgetType;
        this.isActive = isActive;
        this.isMandatory = isMandatory;
        this.slNo = slNo;
        this.cmd = cmd;
        this.defaultVal = defaultVal;
        this.helpText = helpText;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

}
