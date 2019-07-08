package com.di.jav.testprojectzazend;

import android.graphics.Bitmap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Person {
    private Long mId;
    private String firstName;
    private String lastName;
    private LocalDate mDateOfBirth;
    private Integer mAge;
    private Bitmap mPhoto;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return mAge;
    }

    public void setAge(Integer age) {
        mAge = age;
    }

    public Bitmap getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Bitmap photo) {
        mPhoto = photo;
    }

    @Override
    public String toString() {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(mDateOfBirth);
    }
}
