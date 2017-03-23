package org.reflection.model.com;

import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "ADM_SUB_MODULE")
public class AdmSubModule extends AbstractAdm implements IDetail {

    @ManyToOne(optional = false)
    @JoinColumn(name = "ADM_MODULE_ID", nullable = false)
    private AdmModule admModule;

    @Column(name = "DISPLAY_ICON_CLASS", length = 30)
    private String displayIconClass;

    @OneToMany(mappedBy = "admSubModule")
    @OrderBy(value = "slNo, fullName")
    private Set<AdmMenuCommon> admMenuCommons = new TreeSet<>();

    @OneToMany(mappedBy = "admSubModule")
    @OrderBy(value = "slNo, fullName")
    private Set<AdmProcess> admProcesss = new TreeSet<>();

    @OneToMany(mappedBy = "admSubModule")
    @OrderBy(value = "slNo, fullName")
    private Set<AdmReport> admReports = new TreeSet<>();

    @Transient
    private CrudType crudType;

    public AdmSubModule() {
    }

    public AdmSubModule(String code, String fullName, AdmModule admModule) {
        super(code, fullName);
        this.admModule = admModule;
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

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }

    public String getDisplayIconClass() {
        return displayIconClass;
    }

    public void setDisplayIconClass(String displayIconClass) {
        this.displayIconClass = displayIconClass;
    }

    public Set<AdmMenuCommon> getAdmMenuCommons() {
        return admMenuCommons;
    }

    public void setAdmMenuCommons(Set<AdmMenuCommon> admMenuCommons) {
        this.admMenuCommons = admMenuCommons;
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
