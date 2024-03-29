package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        String[] arrDate = date.split("-");
        arrDate[2] = arrDate[2].substring(0,2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(arrDate[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(arrDate[1]));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrDate[2]));
        Date d = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(d);
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
