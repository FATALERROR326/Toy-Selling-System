package com.company.Client;

public class RequestEntity {
    private String method;
    private String toyName;

    public RequestEntity(String method, String toyName) {
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