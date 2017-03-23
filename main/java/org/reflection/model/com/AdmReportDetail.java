package org.reflection.model.com;

import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;

@Entity
@Table(name = "ADM_REPORT_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ADM_REPORT_ID", "ADM_PARAM_ID"})})
public class AdmReportDetail extends Abstract implements IDetail {

    @Column(name = "SL_NO")
    private Byte slNo;
    @JoinColumn(name = "ADM_REPORT_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmReport admReport;
    @JoinColumn(name = "ADM_PARAM_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmParam admParam;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT")
    private String helpText;

    @Transient
    private CrudType crudType;

    public AdmReportDetail() {
    }

    public AdmParam getAdmParam() {
        return admParam;
    }

    public void setAdmParam(AdmParam admParam) {
        this.admParam = admParam;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public AdmReport getAdmReport() {
        return admReport;
    }

    public void setAdmReport(AdmReport admReport) {
        this.admReport = admReport;
    }

    @Override
    public String toString() {
        return admParam.getCode();
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

    @Override
    public CrudType getCrudType() {
        return crudType;
    }

    @Override
    public void setCrudType(CrudType crudType) {
        this.crudType = crudType;
    }
}
