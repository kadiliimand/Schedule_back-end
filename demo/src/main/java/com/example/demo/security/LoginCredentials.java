package com.example.demo.security;

public class LoginCredentials {
    public Object getRawPassword;
    private String idNumber;
    private String password;

    public String getIdNumber() {
        return idNumber;
    }

    public Object getGetRawPassword() {
        return getRawPassword;
    }

    public void setGetRawPassword(Object getRawPassword) {
        this.getRawPassword = getRawPassword;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
