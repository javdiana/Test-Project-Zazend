package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("results")
    @Expose
    private Person[] mPerson;

    @SerializedName("info")
    @Expose
    private Info info;

    public Person[] getPerson() {
        return mPerson;
    }

    public void setPerson(Person[] person) {
        mPerson = person;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}
