package org.reflection.model.com;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ABSTRACT_ADM")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public abstract class AbstractAdm extends AbstractLookable {

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;
    @Column(name = "SL_NO")
    @Range(min = 0, max = 100)
    private Byte slNo;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    public AbstractAdm() {
    }

    public AbstractAdm(String code, String fullName) {
        super(code, fullName);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
