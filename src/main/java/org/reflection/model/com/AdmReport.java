package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.reflection.model.com.enums.ReportFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "ADM_REPORT")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmReport extends AbstractAdm {

    @JoinColumn(name = "ADM_SUB_MODULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmSubModule admSubModule;
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

    public AdmSubModule getAdmSubModule() {
        return admSubModule;
    }

    public void setAdmSubModule(AdmSubModule admSubModule) {
        this.admSubModule = admSubModule;
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

}
