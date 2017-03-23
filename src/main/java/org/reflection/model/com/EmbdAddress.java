package org.reflection.model.com;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Embeddable
public class EmbdAddress implements Serializable {

    @Column(name = "HOUSE_NO", length = 20)
    private String houseNo;
    @Column(length = 30)
    private String street;
    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;
    @Column(name = "PO_BOX", length = 10)
    private String poBox;
    @Column(length = 30)
    private String city;
    @Column(length = 30)
    private String village;

    @ManyToOne
    @JoinColumn(name = "ADM_LOCATION_ID")
    private AdmLocation admLocation;

    public EmbdAddress() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public AdmLocation getAdmLocation() {
        return admLocation;
    }

    public void setAdmLocation(AdmLocation admLocation) {
        this.admLocation = admLocation;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

}
