package org.reflection.model.pos;

import org.reflection.model.com.IAbstract;
import org.reflection.model.com.ICodeable;
import org.reflection.model.com.INameable;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "POS_PRODUCT_AC")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PosProductAc implements IAbstract, ICodeable, INameable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Version
    private Integer version;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;
    @Column(name = "FULL_NAME_NATIVE", length = 255, nullable = false)
    private String fullNameNative;

    @Enumerated(EnumType.STRING)
    @Column(name = "AC_TYPE", length = 30, nullable = false)
    private EmPosAnalysisCode emPosAnalysisCode;

    public PosProductAc() {
    }

    public PosProductAc(String fullName) {
        this.fullName = fullName;
    }

    public PosProductAc(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getFullNameNative() {
        return fullNameNative;
    }

    public void setFullNameNative(String fullNameNative) {
        this.fullNameNative = fullNameNative;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public EmPosAnalysisCode getEmPosAnalysisCode() {
        return emPosAnalysisCode;
    }

    public void setEmPosAnalysisCode(EmPosAnalysisCode emPosAnalysisCode) {
        this.emPosAnalysisCode = emPosAnalysisCode;
    }

    @Override
    public String toString() {
        return code + "-" + fullName;
    }
}

