package com.example.deliverybox.Model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account{
    private int id_account;
    private String email;
    private String password;
    private String full_name;
    private String address;
    private String phone_number;
    private int type;

    public Account() {

    }

    public int getId_account() {
        return id_account;
    }
    public void setId_account(int id_account) {
        this.id_account = id_account;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id_account=" + id_account +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", type=" + type +
                '}';
    }
}
