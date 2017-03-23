package org.reflection.model.auth;

import org.reflection.model.com.Abstract;
import org.springframework.http.HttpMethod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AUTH_REQUEST_MAP")
@XmlRootElement
public class AuthRequestMap extends Abstract {

    @Column(name = "CONFIG_ATTRIBUTE", nullable = false)
    private String configAttribute;
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;
    @Column(name = "URL", nullable = false, unique = true)
    private String url;

    public AuthRequestMap() {
    }

    public AuthRequestMap(String url, String configAttribute) {
        this(url, configAttribute, null);
    }

    public AuthRequestMap(String url, String configAttribute, HttpMethod httpMethod) {

        this.configAttribute = configAttribute;
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public String getConfigAttribute() {
        return configAttribute;
    }

    public void setConfigAttribute(String configAttribute) {
        this.configAttribute = configAttribute;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AuthRequestMap{" + "configAttribute=" + configAttribute + ", httpMethod=" + httpMethod + ", url=" + url + '}';
    }

}
