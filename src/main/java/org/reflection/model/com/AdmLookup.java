package org.reflection.model.com;

import com.oith.annotation.MacSearchable;
import org.reflection.model.com.enums.LookupKeyword;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ADM_LOOKUP", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"LOOKUP_KEYWORD", "FULL_NAME"})})
@XmlRootElement
public class AdmLookup extends AbstractLookable {

    @Enumerated(EnumType.STRING)
    @Column(name = "LOOKUP_KEYWORD", length = 30)
    private LookupKeyword lookupKeyword;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Byte slNo;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public AdmLookup() {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LookupKeyword getLookupKeyword() {
        return lookupKeyword;
    }

    public void setLookupKeyword(LookupKeyword lookupKeyword) {
        this.lookupKeyword = lookupKeyword;
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
