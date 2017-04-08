package org.reflection.model.pos;

import com.oith.annotation.MacCodable;

import javax.persistence.*;

@Entity
@Table(name = "POS_SUPPLIER")
@MacCodable(id = "id", code = "mobile", caption = "fullName")
public class PosSupplier extends PosProductAc {

    @Column(name = "MOBILE", length = 20, nullable = false, unique = true)
    private String mobile;
    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUPPLIER_GROUP", length = 30, nullable = false)
    private EmPosSupplierGroup emPosSupplierGroup;

    public PosSupplier() {
    }

    public PosSupplier(String mobile, String fullName) {
        this.mobile = mobile;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return mobile + "-" + getFullName();
    }

    public EmPosSupplierGroup getEmPosSupplierGroup() {
        return emPosSupplierGroup;
    }

    public void setEmPosSupplierGroup(EmPosSupplierGroup emPosSupplierGroup) {
        this.emPosSupplierGroup = emPosSupplierGroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
