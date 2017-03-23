package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacSearchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADM_CODE_DEF")
@MacCodable(id = "id", code = "pojoClass", caption = "fullName")
public class AdmCodeDef extends Abstract {

    @Column(name = "FULL_NAME", length = 100, nullable = false, unique = true)
    private String fullName;
    @MacSearchable
    @Column(name = "POJO_CLASS", length = 100, nullable = false, unique = true)
    private String pojoClass;
    @Column(name = "START_WITH", length = 10)
    private String startWith;
    @Column(name = "END_WITH", length = 10)
    private String endWith;
    @Column(name = "CODE_LENGTH")
    private Integer codeLength;
    @Column(name = "NEXT_VALUE", nullable = false)
    private Long nextValue = 1L;

    public AdmCodeDef() {
    }

    public AdmCodeDef(String pojoClass, String startWith, Integer codeLength, String endWith) {
        this.pojoClass = pojoClass;
        this.startWith = startWith;
        this.codeLength = codeLength;
        this.endWith = endWith;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Long getNextValue() {
        return nextValue;
    }

    public void setNextValue(Long nextValue) {
        this.nextValue = nextValue;
    }

    public String getPojoClass() {
        return pojoClass;
    }

    public void setPojoClass(String pojoClass) {
        this.pojoClass = pojoClass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    public String getEndWith() {
        return endWith;
    }

    public void setEndWith(String endWith) {
        this.endWith = endWith;
    }

}
