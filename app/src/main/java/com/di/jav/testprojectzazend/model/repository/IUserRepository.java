package com.di.jav.testprojectzazend.model.repository;

import rx.Subscription;

public interface IUserRepository {
    void getPeople(String seed);

    void findByFirstOrLastName(String key);
}
