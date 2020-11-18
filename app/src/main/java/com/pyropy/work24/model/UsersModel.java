package com.pyropy.work24.model;

public class UsersModel {
    public String email;
    public String phone;
    public String usertype, display_img,fullname,password;

    public UsersModel() {
    }

    public UsersModel(String email, String phone, String usertype, String display_img, String fullname, String password) {
        this.email = email;
        this.phone = phone;
        this.usertype = usertype;
        this.display_img = display_img;
        this.fullname = fullname;
        this.password = password;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getDisplay_img() {
        return display_img;
    }

    public void setDisplay_img(String display_img) {
        this.display_img = display_img;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
