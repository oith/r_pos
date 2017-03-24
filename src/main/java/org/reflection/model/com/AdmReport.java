package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.hibernate.validator.constraints.Range;
import org.reflection.model.com.enums.ReportFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "ADM_REPORT")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmReport extends AbstractLookable {

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;
    @Column(name = "SL_NO")
    @Range(min = 0, max = 100)
    private Byte slNo;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @JoinColumn(name = "ADM_MODULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmModule admModule;
    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "FILE_NAME", unique = true)
    private String fileName;
    @OneToMany(mappedBy = "admReport")
    @OrderBy(value = "slNo DESC")
    private Set<AdmReportDetail> admReportDetails;

    //@OneToMany
    //@JoinColumn(name = "ADM_REPORT_SUPPORT_FORMAT")
    @ElementCollection(targetClass = ReportFormat.class)
    @JoinTable(
            name = "ADM_REPORT_SUPPORT_FORMAT",
            joinColumns = @JoinColumn(name = "ADM_REPORT_ID"))
    @Column(name = "SUPPORT_FORMAT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ReportFormat> supportFormats;

    public AdmReport() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<AdmReportDetail> getAdmReportDetails() {
        return admReportDetails;
    }

    public void setAdmReportDetails(Set<AdmReportDetail> admReportDetails) {
        this.admReportDetails = admReportDetails;
    }

    public Set<ReportFormat> getSupportFormats() {
        return supportFormats;
    }

    public void setSupportFormats(Set<ReportFormat> supportFormats) {
        this.supportFormats = supportFormats;
    }

    @Override
    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public Byte getSlNo() {
        return slNo;
    }

    @Override
    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }
}
