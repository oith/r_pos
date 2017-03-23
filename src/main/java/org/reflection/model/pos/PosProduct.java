package org.reflection.model.pos;

import com.oith.annotation.MacCodable;
import org.reflection.model.com.IAbstract;
import org.reflection.model.com.ICodeable;
import org.reflection.model.com.INameable;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "POS_PRODUCT")
@MacCodable(id = "id", code = "code", caption = "fullName")
public class PosProduct implements IAbstract, ICodeable, INameable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Version
    private Integer version;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;
    @Column(name = "FULL_NAME", length = 30, nullable = false)
    private String fullName;
    @Column(name = "FULL_NAME_NATIVE", length = 50, nullable = false)
    private String fullNameNative;

    @Column(name = "TAG_CODE", length = 10, unique = true)
    private String tagCode;

    @Column(name = "IS_VAT_CALC")
    private Boolean isVatCalc;

    @Column(name = "LIMIT_MAX")
    private Double limitMax;
    @Column(name = "LIMIT_STD")
    private Double limitStd;
    @Column(name = "LIMIT_MIN")
    private Double limitMin;

    @Column(name = "UNIT_PRICE_PURCHASE_STD", nullable = false)
    private Double unitPricePurchaseStd;
    @Column(name = "UNIT_PRICE_PURCHASE_MIN")
    private Double unitPricePurchaseMin;
    @Column(name = "UNIT_PRICE_PURCHASE_MAX")
    private Double unitPricePurchaseMax;

    @Column(name = "UNIT_PRICE_SALES_STD", nullable = false)
    private Double unitPriceSalesStd;
    @Column(name = "UNIT_PRICE_SALES_MIN")
    private Double unitPriceSalesMin;
    @Column(name = "UNIT_PRICE_SALES_max")
    private Double unitPriceSalesMax;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "AC_ONE")
    private PosProductAc acOne;
    @ManyToOne
    @JoinColumn(name = "AC_TWO")
    private PosProductAc acTwo;
    @ManyToOne
    @JoinColumn(name = "AC_THREE")
    private PosProductAc acThree;
    @ManyToOne
    @JoinColumn(name = "AC_FOUR")
    private PosProductAc acFour;
    @ManyToOne
    @JoinColumn(name = "AC_FIVE")
    private PosProductAc acFive;

    public PosProduct() {
    }

    public PosProduct(String code, String fullName,
                      Double unitPricePurchase,
                      Double unitPriceSalesDef) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Boolean getIsVatCalc() {
        return isVatCalc;
    }

    public void setIsVatCalc(Boolean isVatCalc) {
        this.isVatCalc = isVatCalc;
    }

    public Double getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(Double limitMax) {
        this.limitMax = limitMax;
    }

    public Double getLimitStd() {
        return limitStd;
    }

    public void setLimitStd(Double limitStd) {
        this.limitStd = limitStd;
    }

    public Double getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(Double limitMin) {
        this.limitMin = limitMin;
    }

    public Double getUnitPricePurchaseStd() {
        return unitPricePurchaseStd;
    }

    public void setUnitPricePurchaseStd(Double unitPricePurchaseStd) {
        this.unitPricePurchaseStd = unitPricePurchaseStd;
    }

    public Double getUnitPricePurchaseMin() {
        return unitPricePurchaseMin;
    }

    public void setUnitPricePurchaseMin(Double unitPricePurchaseMin) {
        this.unitPricePurchaseMin = unitPricePurchaseMin;
    }

    public Double getUnitPricePurchaseMax() {
        return unitPricePurchaseMax;
    }

    public void setUnitPricePurchaseMax(Double unitPricePurchaseMax) {
        this.unitPricePurchaseMax = unitPricePurchaseMax;
    }

    public Double getUnitPriceSalesStd() {
        return unitPriceSalesStd;
    }

    public void setUnitPriceSalesStd(Double unitPriceSalesStd) {
        this.unitPriceSalesStd = unitPriceSalesStd;
    }

    public Double getUnitPriceSalesMin() {
        return unitPriceSalesMin;
    }

    public void setUnitPriceSalesMin(Double unitPriceSalesMin) {
        this.unitPriceSalesMin = unitPriceSalesMin;
    }

    public Double getUnitPriceSalesMax() {
        return unitPriceSalesMax;
    }

    public void setUnitPriceSalesMax(Double unitPriceSalesMax) {
        this.unitPriceSalesMax = unitPriceSalesMax;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public String getFullNameNative() {
        return fullNameNative;
    }

    @Override
    public void setFullNameNative(String fullNameNative) {
        this.fullNameNative = fullNameNative;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public PosProductAc getAcOne() {
        return acOne;
    }

    public void setAcOne(PosProductAc acOne) {
        this.acOne = acOne;
    }

    public PosProductAc getAcTwo() {
        return acTwo;
    }

    public void setAcTwo(PosProductAc acTwo) {
        this.acTwo = acTwo;
    }

    public PosProductAc getAcThree() {
        return acThree;
    }

    public void setAcThree(PosProductAc acThree) {
        this.acThree = acThree;
    }

    public PosProductAc getAcFour() {
        return acFour;
    }

    public void setAcFour(PosProductAc acFour) {
        this.acFour = acFour;
    }

    public PosProductAc getAcFive() {
        return acFive;
    }

    public void setAcFive(PosProductAc acFive) {
        this.acFive = acFive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosProduct that = (PosProduct) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
