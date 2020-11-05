package com.pyropy.work24.model;

public class UsersModel {
    public String email;
    public String phone;
    public String userType;

    public UsersModel() {
    }

    public UsersModel(String email, String phone, String userType) {
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
