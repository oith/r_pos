package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "ADM_PROCESS")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmProcess  extends AbstractLookable {

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
    @Size(min = 3, max = 2000)
    private String cmd;
    @Size(max = 2000)
    private String query;
    @Column(name = "QUERY_ALIAS", length = 500)
    private String queryAlias;
    @OneToMany(mappedBy = "admProcess")
    @OrderBy(value = "slNo DESC")
    private Set<AdmProcessDetail> admProcessDetails;
    @Column(name = "PROCESS_BTNS", length = 1000)
    private String processBtns;

    public AdmProcess() {
    }


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryAlias() {
        return queryAlias;
    }

    public void setQueryAlias(String queryAlias) {
        this.queryAlias = queryAlias;
    }

    public Set<AdmProcessDetail> getAdmProcessDetails() {
        return admProcessDetails;
    }

    public void setAdmProcessDetails(Set<AdmProcessDetail> admProcessDetails) {
        this.admProcessDetails = admProcessDetails;
    }

    public String getProcessBtns() {
        return processBtns;
    }

    public void setProcessBtns(String processBtns) {
        this.processBtns = processBtns;
    }

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Byte getSlNo() {
        return slNo;
    }

    @Override
    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    @Override
    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
