package org.reflection.model.com;

import org.reflection.model.auth.AuthUser;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Embeddable
public class EmbdProcess implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTH_USER_RUN_BY_ID", nullable = false)
    private AuthUser authUserRunBy;

    @ManyToOne
    @JoinColumn(name = "AUTH_USER_ALTER_BY_ID")
    private AuthUser authUserAlterBy;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "PROC_DATE", nullable = false)
    private Date procDate;
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Column(name = "IS_PROC_PASS", nullable = false)
    private Boolean isProcPass = Boolean.TRUE;

    public AuthUser getAuthUserRunBy() {
        return authUserRunBy;
    }

    public void setAuthUserRunBy(AuthUser authUserRunBy) {
        this.authUserRunBy = authUserRunBy;
    }

    public AuthUser getAuthUserAlterBy() {
        return authUserAlterBy;
    }

    public void setAuthUserAlterBy(AuthUser authUserAlterBy) {
        this.authUserAlterBy = authUserAlterBy;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsProcPass() {
        return isProcPass;
    }

    public void setIsProcPass(Boolean isProcPass) {
        this.isProcPass = isProcPass;
    }

}
