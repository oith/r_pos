package org.reflection.model.com;

import com.oith.annotation.MacCodable;
import org.reflection.model.auth.AuthUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ADM_TRANSACTION_QUANTITY")
@MacCodable(id = "id", code = "code", caption = "desc")
public abstract class AbstractTransactableQuantity extends AbstractCodeable implements ITransactableQuantity {

    @Embedded
    public EmbdAuditable embdAuditable;
    @Temporal(TemporalType.DATE)
    @Column(name = "TRANS_DATE", nullable = false)
    private Date transDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORIGIN_DATE", nullable = false)
    private Date originDate = new Date();
    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;
    @Column(name = "REMARKS", length = 500)
    private String remarks;
    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTH_USER_TRANS_BY_ID", nullable = false)
    private AuthUser authUserTransBy;


    public AbstractTransactableQuantity() {

    }

    public AbstractTransactableQuantity(Date transDate, Double quantity, AuthUser authUserTransBy) {
        this(transDate, quantity, authUserTransBy, transDate);
    }

    public AbstractTransactableQuantity(Date transDate, Double quantity, AuthUser authUserTransBy, Date originDate) {
        this.transDate = transDate;
        this.quantity = quantity;
        this.authUserTransBy = authUserTransBy;
        this.originDate = originDate;
    }

    public AbstractTransactableQuantity(AbstractTransactableQuantity abstractTransactableAmount) {
        this.transDate = abstractTransactableAmount.transDate;
        this.quantity = abstractTransactableAmount.quantity;
        this.authUserTransBy = abstractTransactableAmount.authUserTransBy;
        this.originDate = abstractTransactableAmount.originDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
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
    public EmbdAuditable getEmbdAuditable() {
        return embdAuditable;
    }

    @Override
    public void setEmbdAuditable(EmbdAuditable embdAuditable) {
        this.embdAuditable = embdAuditable;
    }

    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
