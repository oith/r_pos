package org.reflection.model.sample;

import com.oith.annotation.MacSearchable;
import org.reflection.model.com.Abstract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ZX_DESG")
@XmlRootElement
public class ZxDesg extends Abstract {

    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Byte slNo;
    @MacSearchable
    @Column(name = "TITLE", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String title;
    @Column(name = "TITLE_NATIVE", length = 100)
    private String titleNative;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public ZxDesg() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNative() {
        return titleNative;
    }

    public void setTitleNative(String titleNative) {
        this.titleNative = titleNative;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return title;
    }
}
