package com.hms.HostelManagement.model;

import java.util.Date;

public class Student extends User {
    private String rollNo;
    private String name;
    private String phone;
    private Date dob;
    private String address;
    private String email;
    private String bio;

    public Date getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }



    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }



    public String getRollNo() {
        return rollNo;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}
