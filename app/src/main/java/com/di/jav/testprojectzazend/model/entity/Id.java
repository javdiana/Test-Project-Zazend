package com.di.jav.testprojectzazend.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {
    @SerializedName("name")
    @Expose
    private Long name;

    @SerializedName("value")
    @Expose
    private Long value;

    public Id(Long name, Long value) {
        this.name = name;
        this.value = value;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
