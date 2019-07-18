package com.di.jav.testprojectzazend.activity.personslist;

import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.repository.IUserRepository;
import com.di.jav.testprojectzazend.model.repository.UserRepository;

import java.util.List;

import rx.Subscription;

public class PersonsListPresenterImp implements IPersonsList.IPersonsListPresenter {
    private IUserRepository mUserRepository;
    private GetUserCallBack mCallBack;


    public PersonsListPresenterImp() {
        mCallBack = new UserRepository();
    }

    @Override
    public void getData(List<Person> people, String seed, String name) {
        mCallBack.getData(people, seed, name);
    }
}
