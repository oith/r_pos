package org.reflection.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class _DualDTO implements Serializable {

    private BigInteger id;
    private String title;

    public _DualDTO() {
    }

    public _DualDTO(BigInteger id, String title) {
        this.id = id;
        this.title = title;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return title;
    }
}
