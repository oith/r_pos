package org.reflection.model.com;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ABSTRACT_CODEABLE")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCodeable extends Abstract implements ICodeable {

    @Size(min = 2, max = 6)
    @Column(name = "CODE", unique = true, nullable = false, length = 20)
    private String code;

    public AbstractCodeable() {
    }

    public AbstractCodeable(String code) {
        this.code = code;

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
    public String toString() {
        return code;
    }
}
