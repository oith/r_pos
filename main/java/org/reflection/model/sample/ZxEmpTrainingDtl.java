package org.reflection.model.sample;

import com.oith.annotation.MacFile;
import com.oith.annotation.MacImagable;
import org.reflection.model.com.Abstract;
import org.reflection.model.com.IDetail;
import org.reflection.model.com.enums.CrudType;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "ZX_EMP_TRAINING_DTL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ZX_EMP_ID", "ZX_LOOKUP_TRAINING_ID"})})
@XmlRootElement
public class ZxEmpTrainingDtl extends Abstract implements IDetail {

    @ManyToOne(optional = false)
    @JoinColumn(name = "ZX_EMP_ID", nullable = false)
    private ZxEmp zxEmp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ZX_LOOKUP_TRAINING_ID", nullable = false)
    private ZxLookup zxLookupTraining;

    @Basic(optional = false)
    @Column(name = "TRAINING_ORG", nullable = false, length = 20)
    private String trainingOrg;

    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    @Past
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    @Past
    private Date toDate;
    @Column(length = 500)
    private String remarks;
    @Column(name = "SL_NO")
    private Byte slNo;
    @MacFile(min = 1, max = 1000000, extention = "doc, pdf, docx")
    @Column(name = "CERTIFICATE")
    private String certificate;
    @MacImagable
    @Column(name = "PIC")
    private String pic;

    @ManyToOne
    @JoinColumn(name = "ZX_EMP_WHO_CHECKED_BY_ID")
    private ZxEmp zxEmpWhoCheckedBy;

    @Transient
    private CrudType crudType;

    public ZxEmpTrainingDtl() {
    }

    public ZxEmp getZxEmp() {
        return zxEmp;
    }

    public void setZxEmp(ZxEmp zxEmp) {
        this.zxEmp = zxEmp;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ZxEmp getZxEmpWhoCheckedBy() {
        return zxEmpWhoCheckedBy;
    }

    public void setZxEmpWhoCheckedBy(ZxEmp zxEmpWhoCheckedBy) {
        this.zxEmpWhoCheckedBy = zxEmpWhoCheckedBy;
    }

    public ZxLookup getZxLookupTraining() {
        return zxLookupTraining;
    }

    public void setZxLookupTraining(ZxLookup zxLookupTraining) {
        this.zxLookupTraining = zxLookupTraining;
    }

    public String getTrainingOrg() {
        return trainingOrg;
    }

    public void setTrainingOrg(String trainingOrg) {
        this.trainingOrg = trainingOrg;
    }

    @Override
    public String toString() {
        return zxLookupTraining.toString();
    }

    @Override
    public CrudType getCrudType() {
        return crudType;
    }

    @Override
    public void setCrudType(CrudType crudType) {
        this.crudType = crudType;
    }
}
