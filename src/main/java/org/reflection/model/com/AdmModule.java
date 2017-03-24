package org.reflection.model.com;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "ADM_MODULE")
public class AdmModule extends AbstractLookable {

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;
    @Column(name = "SL_NO")
    @Range(min = 0, max = 100)
    private Byte slNo;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @OneToMany(mappedBy = "admModule")
    @OrderBy(value = "slNo, fullName")
    private Set<AdmProcess> admProcesss = new TreeSet();

    @OneToMany(mappedBy = "admModule")
    @OrderBy(value = "slNo, fullName")
    private Set<AdmReport> admReports = new TreeSet();

    public AdmModule() {
    }

    public AdmModule(String code, String fullName) {
        super(code, fullName);
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AdmProcess> getAdmProcesss() {
        return admProcesss;
    }

    public void setAdmProcesss(Set<AdmProcess> admProcesss) {
        this.admProcesss = admProcesss;
    }

    public Set<AdmReport> getAdmReports() {
        return admReports;
    }

    public void setAdmReports(Set<AdmReport> admReports) {
        this.admReports = admReports;
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
