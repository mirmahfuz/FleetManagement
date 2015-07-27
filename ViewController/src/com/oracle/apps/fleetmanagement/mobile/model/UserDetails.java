package com.oracle.apps.fleetmanagement.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;


public class UserDetails extends Entity {

    private String zip;
    private String lastName;
    private String username;
    private String phone;
    private String address;
    private String email;
    private String userId;
    private String firstName;
    private String password;
    private String city;


    public String getZip() {
        return this.zip==null?"94086":this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLastName() {
        return this.lastName==null?"Doe":this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username==null?"john.doe":this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return this.phone==null?"3332341234":this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address==null?"300 Oracle Parkway":this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email==null?"john.doe@oracle.com":this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName==null?"John":this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return this.city==null?"Redwood City":this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
