package com.di.jav.testprojectzazend.activity.personslist;

import com.di.jav.testprojectzazend.model.entity.Person;

import java.util.List;

import rx.Subscription;

public interface GetUserCallBack {
    void getData(List<Person> people, String seed, String name);
}
