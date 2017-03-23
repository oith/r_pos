package org.reflection.model.sample;

import org.reflection.model.com.Abstract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ZX_CHOOSE")
public class ZxChoose extends Abstract {

    @Column(name = "CHOOSE", length = 50, unique = true, nullable = false)
    private String choose;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = Boolean.TRUE;

    public ZxChoose() {
        this("Java");
    }

    public ZxChoose(String choose) {
        this.choose = choose;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return choose;
    }
}
