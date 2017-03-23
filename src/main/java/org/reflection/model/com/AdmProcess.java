package org.reflection.model.com;

import com.oith.annotation.MacCodable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "ADM_PROCESS")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class AdmProcess extends AbstractAdm {

    @JoinColumn(name = "ADM_SUB_MODULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmSubModule admSubModule;
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

    public AdmSubModule getAdmSubModule() {
        return admSubModule;
    }

    public void setAdmSubModule(AdmSubModule admSubModule) {
        this.admSubModule = admSubModule;
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

}
