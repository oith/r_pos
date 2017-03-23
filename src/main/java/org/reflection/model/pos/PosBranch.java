package org.reflection.model.pos;

import com.oith.annotation.MacCodable;
import org.reflection.model.com.Abstract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POS_BRANCH")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class PosBranch extends Abstract {

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;
    @Column(name = "FULL_NAME", length = 30, nullable = false)
    private String fullName;
    @Column(name = "ADDRESS", length = 200)
    private String address;

    public PosBranch() {
    }

    public PosBranch(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code + "-" + fullName;
    }


}
