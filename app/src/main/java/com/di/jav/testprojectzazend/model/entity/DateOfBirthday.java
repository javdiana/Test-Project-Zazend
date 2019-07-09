package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateOfBirthday {
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("age")
    @Expose
    private Integer age;

    public DateOfBirthday(String date, Integer age) {
        this.date = date;
        this.age = age;
    }

    public String getDate() {
//        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
