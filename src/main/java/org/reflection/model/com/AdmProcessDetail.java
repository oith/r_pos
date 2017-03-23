package org.reflection.model.com;

import org.reflection.model.com.enums.CrudType;
import org.reflection.model.com.enums.ZoneType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADM_PROCESS_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ADM_PROCESS_ID", "ADM_PARAM_ID", "ZONE_TYPE"})})
public class AdmProcessDetail extends Abstract implements IDetail {

    @JoinColumn(name = "ADM_PROCESS_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmProcess admProcess;

    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmParam admParam;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ZONE_TYPE", length = 30)
    private ZoneType zoneType;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT", length = 200)
    private String helpText;
    @Column(name = "SL_NO")
    private Byte slNo;

    @Transient
    private CrudType crudType;

    public AdmProcessDetail() {
    }

    public AdmParam getAdmParam() {
        return admParam;
    }

    public void setAdmParam(AdmParam admParam) {
        this.admParam = admParam;
    }

    @Override
    public String toString() {
        return admParam.getCode();
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public AdmProcess getAdmProcess() {
        return admProcess;
    }

    public void setAdmProcess(AdmProcess admProcess) {
        this.admProcess = admProcess;
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

    public ZoneType getZoneType() {
        return zoneType;
    }

    public void setZoneType(ZoneType zoneType) {
        this.zoneType = zoneType;
    }

    @Override
    public CrudType getCrudType() {
        return crudType;
    }

    @Override
    public void setCrudType(CrudType crudType) {
        this.crudType = crudType;
    }
}
