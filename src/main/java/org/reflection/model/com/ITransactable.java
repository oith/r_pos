package org.reflection.model.com;

import org.reflection.model.auth.AuthUser;

import java.util.Date;

public interface ITransactable extends ICodeable, IEmbdAuditable {

    public Date getTransDate();

    public void setTransDate(Date transDate);

    public String getRemarks();

    public void setRemarks(String remarks);

    public AuthUser getAuthUserTransBy();

    public void setAuthUserTransBy(AuthUser authUserTransBy);
}
