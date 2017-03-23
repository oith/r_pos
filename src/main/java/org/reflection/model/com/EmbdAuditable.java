package org.reflection.model.com;

import com.oith.annotation.Action;
import com.oith.annotation.MacSilent;
import org.reflection.model.auth.AuthUser;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Embeddable
public class EmbdAuditable implements Serializable {

    @MacSilent(actions = {Action.SAVE}, value = "currAuthUser()")
    @JoinColumn(name = "ENTRY_BY_ID", nullable = false)
    @ManyToOne(optional = false)
    private AuthUser entryBy;

    @MacSilent(actions = {Action.SAVE}, value = "new java.util.Date()")
    @Column(name = "ENTRY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @MacSilent(actions = {Action.UPDATE}, value = "currAuthUser()")
    @JoinColumn(name = "EDIT_BY_ID")
    @ManyToOne
    private AuthUser editBy;

    @MacSilent(actions = {Action.UPDATE}, value = "new java.util.Date()")
    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @MacSilent(actions = {Action.COPY}, value = "currAuthUser()")
    @JoinColumn(name = "COPY_BY_ID")
    @ManyToOne
    private AuthUser copyBy;

    @MacSilent(actions = {Action.COPY}, value = "new java.util.Date()")
    @Column(name = "COPY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date copyDate;

    public EmbdAuditable() {

    }

    public EmbdAuditable(AuthUser entryBy, Date entryDate) {
        this.entryBy = entryBy;
        this.entryDate = entryDate;
    }

    public AuthUser getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(AuthUser entryBy) {
        this.entryBy = entryBy;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public AuthUser getEditBy() {
        return editBy;
    }

    public void setEditBy(AuthUser editBy) {
        this.editBy = editBy;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public AuthUser getCopyBy() {
        return copyBy;
    }

    public void setCopyBy(AuthUser copyBy) {
        this.copyBy = copyBy;
    }

    public Date getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(Date copyDate) {
        this.copyDate = copyDate;
    }

}
