package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("results")
    @Expose
    private List<Person> mPerson;

    @SerializedName("info")
    @Expose
    private Info info;

    public List<Person> getPerson() {
        return mPerson;
    }

    public void setPerson(List<Person> person) {
        this.mPerson = person;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}
