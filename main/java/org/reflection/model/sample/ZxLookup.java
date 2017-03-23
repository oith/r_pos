package org.reflection.model.sample;

import com.oith.annotation.Action;
import com.oith.annotation.MacSearchable;
import com.oith.annotation.MacSilent;
import org.reflection.model.com.Abstract;
import org.reflection.model.com.EmbdAuditable;
import org.reflection.model.com.IEmbdAuditable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ZX_LOOKUP", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ZX_LOOKUP_KEYWORD", "TITLE"})})
@XmlRootElement
public class ZxLookup extends Abstract implements IEmbdAuditable {

    @MacSilent(actions = {Action.SAVE, Action.COPY}, value = "genCode(\"ZxLookup\",\"LK-\",4)")
    @Column(name = "CODE", nullable = false, unique = true, length = 20)
    private String code;
    @MacSearchable
    @Column(name = "TITLE", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String title;
    @Column(name = "TITLE_NATIVE", length = 500)
    private String titleNative;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    @Max(value = 80)
    @Min(value = -60)
    private Byte slNo;

    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Enumerated(EnumType.STRING)
    @Column(name = "ZX_LOOKUP_KEYWORD", length = 30)
    private ZxLookupKeyword zxLookupKeyword;

    @Embedded
    private EmbdAuditable embdAuditable = new EmbdAuditable();

    public ZxLookup() {
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

    public ZxLookupKeyword getZxLookupKeyword() {
        return zxLookupKeyword;
    }

    public void setZxLookupKeyword(ZxLookupKeyword zxLookupKeyword) {
        this.zxLookupKeyword = zxLookupKeyword;
    }

    @Override
    public EmbdAuditable getEmbdAuditable() {
        return embdAuditable;
    }

    @Override
    public void setEmbdAuditable(EmbdAuditable embdAuditable) {
        this.embdAuditable = embdAuditable;
    }

    @Override
    public String toString() {
        return title;
    }
}
