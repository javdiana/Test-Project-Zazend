package com.di.jav.testprojectzazend.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {
    @SerializedName("gender")
    @Expose
    private String mGender;

    @SerializedName("name")
    @Expose
    private Name mName;

    @SerializedName("location")
    @Expose
    private Location mLocation;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("login")
    @Expose
    private Login mLogin;

    @SerializedName("dob")
    @Expose
    private DateOfBirthday mDateOfBirthday;

    @SerializedName("registered")
    @Expose
    private Registered mRegistered;

    @SerializedName("phone")
    @Expose
    private String mPhone;

    @SerializedName("cell")
    @Expose
    private String mCell;

    @SerializedName("id")
    @Expose
    private Id mId;

    @SerializedName("picture")
    @Expose
    private Picture mPicture;

    @SerializedName("nat")
    @Expose
    private String mNat;


    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
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

    public DateOfBirthday getDateOfBirthday() {
        return mDateOfBirthday;
    }

    public void setDateOfBirthday(DateOfBirthday dateOfBirthday) {
        this.mDateOfBirthday = dateOfBirthday;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture picture) {
        mPicture = picture;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public Login getLogin() {
        return mLogin;
    }

    public void setLogin(Login login) {
        this.mLogin = login;
    }

    public Registered getRegistered() {
        return mRegistered;
    }

    public void setRegistered(Registered registered) {
        this.mRegistered = registered;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getCell() {
        return mCell;
    }

    public void setCell(String cell) {
        this.mCell = cell;
    }

    public String getNat() {
        return mNat;
    }

    public void setNat(String nat) {
        this.mNat = nat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{mName.getFirstName(), mName.getLastName(), mDateOfBirthday.getDate(), mGender, mLocation.getCity(), mLocation.getStreet(), mEmail, mPicture.getLarge()});
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel parcel) {
        String[] data = new String[8];
        parcel.readStringArray(data);

        mName.setFirstName(data[0]);
        mName.setLastName(data[1]);
        mDateOfBirthday.setDate(data[2]);
        setGender(data[3]);
        mLocation.setCity(data[4]);
        mLocation.setStreet(data[5]);
        setEmail(data[6]);
        mPicture.setLarge(data[7]);
    }

}
