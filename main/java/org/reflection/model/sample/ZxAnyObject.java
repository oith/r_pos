package org.reflection.model.sample;

import com.oith.annotation.MacSearchable;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.Abstract;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ZX_ANY_OBJECT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"TITLE"})})
@XmlRootElement
public class ZxAnyObject extends Abstract {

    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String title;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Byte slNo;

    public ZxAnyObject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return title;
    }
}
