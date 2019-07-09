package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("name")
    @Expose
    private Name mName;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("login")
    @Expose
    private Login login;

    @SerializedName("dob")
    @Expose
    private DateOfBirthday dateOfBirth;

    @SerializedName("registered")
    @Expose
    private Registered registered;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("cell")
    @Expose
    private String cell;

    @SerializedName("id")
    @Expose
    private Id mId;

    @SerializedName("picture")
    @Expose
    private Picture mPicture;

    @SerializedName("nat")
    @Expose
    private String nat;

    public Person(Id id, Name name, String gender, DateOfBirthday dateOfBirth, Picture picture, String email, Login login, Registered registered, String phone, String cell, String nat) {
        mId = id;
        mName = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        mPicture = picture;
        this.email = email;
        this.login = login;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.nat = nat;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Id getId() {
        return mId;
    }

    public void setId(Id id) {
        mId = id;
    }

    public Name getName() {
        return mName;
    }

    public void setName(Name name) {
        mName = name;
    }

    public DateOfBirthday getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirthday dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture picture) {
        mPicture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
