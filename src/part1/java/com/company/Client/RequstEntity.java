package com.company.Client;

public class RequstEntity {
    String method;
    String toyName;

    public RequstEntity(String method, String toyName) {
        this.method = method;
        this.toyName = toyName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
}
