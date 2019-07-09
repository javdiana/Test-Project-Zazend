package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("mr")
    @Expose
    private String mr;

    @SerializedName("first")
    @Expose
    private String first;

    @SerializedName("last")
    @Expose
    private String last;

    public Name(String mr, String first, String last) {
        this.mr = mr;
        this.first = first;
        this.last = last;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
