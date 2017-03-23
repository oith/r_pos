package org.reflection.model.com;

import java.util.Date;

public interface IApplicable extends ICodeable, IEmbdAuditable {

    public Date getAppDate();

    public void setAppDate(Date appDate);

    public String getRemarks();

    public void setRemarks(String remarks);
}
