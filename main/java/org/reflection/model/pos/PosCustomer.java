package org.reflection.model.pos;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacImagable;

import javax.persistence.*;

@Entity
@Table(name = "POS_CUSTOMER")
@MacCodable(id = "id", code = "mobile", caption = "fullName")
public class PosCustomer extends PosProductAc {

    @Column(name = "MOBILE", length = 20, nullable = false, unique = true)
    private String mobile;
    @MacImagable
    @Column(name = "PIC")
    private String pic;
    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMER_GROUP", length = 30, nullable = false)
    private EmPosCustomerGroup emPosCustomerGroup = EmPosCustomerGroup.GRADE_A;

    public PosCustomer() {
    }

    public PosCustomer(String mobile, String fullName) {
        super(fullName);
        this.mobile = mobile;
    }

    public PosCustomer(String mobile, String fullName, EmPosCustomerGroup emPosCustomerGroup) {
        this(mobile, fullName);
        this.emPosCustomerGroup = emPosCustomerGroup;
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

    public EmPosCustomerGroup getEmPosCustomerGroup() {
        return emPosCustomerGroup;
    }

    public void setEmPosCustomerGroup(EmPosCustomerGroup emPosCustomerGroup) {
        this.emPosCustomerGroup = emPosCustomerGroup;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
