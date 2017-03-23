package org.reflection.model.com;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@Table( name = "ABSTRACT_LOOKABLE")
@XmlRootElement
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class AbstractLookable extends Abstract implements ILookable {

    @Size(min = 2, max = 30)
    @Column(name = "CODE", unique = true, nullable = false, length = 30)
    private String code;
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @Column(name = "FULL_NAME_NATIVE", length = 100)
    private String fullNameNative;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Byte slNo;

    public AbstractLookable() {
    }

    public AbstractLookable(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameNative() {
        return fullNameNative;
    }

    public void setFullNameNative(String fullNameNative) {
        this.fullNameNative = fullNameNative;
    }

    @Override
    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
    public String toString() {
        return code + "-" + fullName;
    }
}
