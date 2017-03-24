package org.reflection.model.sample;

import com.oith.annotation.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.reflection.model.com.Abstract;
import org.reflection.model.com.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "ZX_EMP")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public class ZxEmp extends Abstract {

    @MacSearchable
    @Column(name = "CODE", length = 20, nullable = false, unique = true)
    private String code;
    @MacSearchable
    @Column(name = "TAG_CODE", length = 20, unique = true)
    private String tagCode;
    @MacImagable
    @Column(name = "PIC")
    private String pic;
    @MacSearchable
    @Column(name = "FULL_NAME", length = 50, nullable = false)
    @Size(min = 2, max = 50)
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6, nullable = false)
    private Gender gender;

    @MacSearchable
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    @Past
    private Date dob;
    @Column(precision = 10, scale = 2)
    @Range(min = 1, max = 100000)
    private Double salary;
    @Digits(integer = 8, fraction = 2)
    @Column(name = "TAX_PAID")
    private BigDecimal taxPaid;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @MacPassword
    @Column(name = "PIN", length = 30)
    private String pin;
    @Column(name = "WEB_ADDRESS", length = 20)
    @URL
    private String webAddress;
    @MacFile(min = 1, max = 1000000, extention = "doc,pdf,docx")
    @Column(name = "DOCUMENT")
    private String document;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @Transient
    @Column(name = "CREATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Transient
    @Column(name = "MODIFICATION_TIME")
    private Date modificationTime;
    @JoinColumn(name = "ZX_DEPT_ID")
    @ManyToOne
    private ZxDept zxDept;
    @JoinColumn(name = "ZX_DESG_ID")
    @ManyToOne
    private ZxDesg zxDesg;

    @ManyToOne
    @JoinColumn(name = "ZX_LOOKUP_BLOOD_GROUP_ID")
    private ZxLookup zxLookupBloodGroup;

    @Column(name = "SL_NO")
    private Byte slNo;

    @ElementCollection
    @CollectionTable(name = "ZX_EMP_PHONES",
            joinColumns = @JoinColumn(name = "ZX_EMP_ID"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<ZxPhoneType, String> phoneNumbers = new LinkedHashMap();

    @ElementCollection
    @MapKeyColumn(name = "NAME")
    @Column(name = "VALUE")
    @CollectionTable(name = "ZX_EMP_ATTRIBUTES",
            joinColumns = @JoinColumn(name = "ZX_EMP_ID"))
    private Map<String, String> attributes = new LinkedHashMap();

    @ElementCollection(targetClass = ZxVacationEntry.class)
    @CollectionTable(name = "ZX_EMP_VACATIONS",
            joinColumns = @JoinColumn(name = "ZX_EMP_ID"))
    @AttributeOverride(name = "daysTaken",
            column = @Column(name = "DAYS_ABS"))
    private Collection vacationBookings = new LinkedList();

    // Using generics in place of a targetClass
    @ElementCollection
    @CollectionTable(name = "ZX_EMP_NICK_NAMES",
            joinColumns = @JoinColumn(name = "ZX_EMP_ID"))
    private Set<String> nickNames = new HashSet();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ZX_EMP_ZX_CHOOSE",
            joinColumns = {
                    @JoinColumn(name = "ZX_EMP_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "ZX_CHOOSE_ID")})
    private Set<ZxChoose> zxChooses = new LinkedHashSet();

    @MacMasterDetail(masterDetailType = MasterDetailType.DIV_BASED)
    @OneToMany(mappedBy = "zxEmp", cascade = CascadeType.ALL)
    @OrderBy(value = "slNo DESC")
    private List<ZxEmpEduDtl> zxEmpEduDtls = new ArrayList();

    @MacMasterDetail(masterDetailType = MasterDetailType.TABLE_BASED)
    @OneToMany(mappedBy = "zxEmp", cascade = CascadeType.ALL)
    @OrderBy(value = "slNo DESC")
    private List<ZxEmpTrainingDtl> zxEmpTrainingDtls = new ArrayList();

    public ZxEmp() {
    }

    public ZxEmp(String code, String fullName, Double salary, String email, Boolean isActive, String remarks) {
        this.code = code;
        this.fullName = fullName;
        this.salary = salary;
        this.email = email;
        this.isActive = isActive;
        this.remarks = remarks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(BigDecimal taxPaid) {
        this.taxPaid = taxPaid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public ZxLookup getZxLookupBloodGroup() {
        return zxLookupBloodGroup;
    }

    public void setZxLookupBloodGroup(ZxLookup zxLookupBloodGroup) {
        this.zxLookupBloodGroup = zxLookupBloodGroup;
    }

    public List<ZxEmpEduDtl> getZxEmpEduDtls() {
        return zxEmpEduDtls;
    }

    public void setZxEmpEduDtls(List<ZxEmpEduDtl> zxEmpEduDtls) {
        this.zxEmpEduDtls = zxEmpEduDtls;
    }

    public ZxDept getZxDept() {
        return zxDept;
    }

    public void setZxDept(ZxDept zxDept) {
        this.zxDept = zxDept;
    }

    public ZxDesg getZxDesg() {
        return zxDesg;
    }

    public void setZxDesg(ZxDesg zxDesg) {
        this.zxDesg = zxDesg;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public Set<ZxChoose> getZxChooses() {
        return zxChooses;
    }

    public void setZxChooses(Set<ZxChoose> zxChooses) {
        this.zxChooses = zxChooses;
    }


    public List<ZxEmpTrainingDtl> getZxEmpTrainingDtls() {
        return zxEmpTrainingDtls;
    }

    public void setZxEmpTrainingDtls(List<ZxEmpTrainingDtl> zxEmpTrainingDtls) {
        this.zxEmpTrainingDtls = zxEmpTrainingDtls;
    }

    public Map<ZxPhoneType, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<ZxPhoneType, String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Set<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Collection getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Collection vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return code + "-" + fullName;
    }
}
