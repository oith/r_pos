package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.reflection.model.auth.AuthUser;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "ADM_TRANSACTION_AMOUNT")
@MacCodable(id = "id", code = "code", caption = "remarks")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractTransactableAmount extends AbstractCodeable implements ITransactableAmount {

    @Embedded
    public EmbdAuditable embdAuditable;
    @Size(min = 2, max = 10)
    @Column(name = "CODE", unique = true, nullable = false, length = 10)
    private String code;
    @Temporal(TemporalType.DATE)
    @Column(name = "TRANS_DATE", nullable = false)
    private Date transDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORIGIN_DATE", nullable = false)
    private Date originDate = new Date();
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTH_USER_TRANS_BY_ID", nullable = false)
    private AuthUser authUserTransBy;


    public AbstractTransactableAmount() {

    }

    public AbstractTransactableAmount(Date transDate, Double amount, AuthUser authUserTransBy) {
        this(transDate, amount, authUserTransBy, transDate);
    }

    public AbstractTransactableAmount(Date transDate, Double amount, AuthUser authUserTransBy, Date originDate) {
        this.transDate = transDate;
        this.amount = amount;
        this.authUserTransBy = authUserTransBy;
        this.originDate = originDate;
    }

    public AbstractTransactableAmount(AbstractTransactableAmount abstractTransactableAmount) {
        this.transDate = abstractTransactableAmount.transDate;
        this.amount = abstractTransactableAmount.amount;
        this.authUserTransBy = abstractTransactableAmount.authUserTransBy;
        this.originDate = abstractTransactableAmount.originDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AuthUser getAuthUserTransBy() {
        return authUserTransBy;
    }

    public void setAuthUserTransBy(AuthUser authUserTransBy) {
        this.authUserTransBy = authUserTransBy;
    }

    public Date getOriginDate() {
        return originDate;
    }

    public void setOriginDate(Date originDate) {
        this.originDate = originDate;
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
    public EmbdAuditable getEmbdAuditable() {
        return embdAuditable;
    }

    @Override
    public void setEmbdAuditable(EmbdAuditable embdAuditable) {
        this.embdAuditable = embdAuditable;
    }
}
