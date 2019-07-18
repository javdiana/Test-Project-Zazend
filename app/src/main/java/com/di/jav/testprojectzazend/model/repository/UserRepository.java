package com.di.jav.testprojectzazend.model.repository;

import android.util.Log;

import com.di.jav.testprojectzazend.activity.personslist.GetUserCallBack;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.entity.Result;
import com.di.jav.testprojectzazend.model.service.http.UserGeneratorClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepository implements IUserRepository, GetUserCallBack {
    private final String TAG = "UserRepository.class";
    private static final int NUMBER_OF_PERSONS = 10;

    private List<Person> mPeople;

    private Subscription mSubscription;

    public void getPeople(String seed) {
        mSubscription = UserGeneratorClient.getInstance()
                .getPeople(NUMBER_OF_PERSONS, seed)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        Log.d(TAG, "In onError()");
                        // TODO: 7/12/2019 send result to screen for fail
                    }

                    @Override
                    public void onNext(Result result) {
                        mPeople = Arrays.asList(result.getPerson());//work, but need more time
                        Log.d(TAG, "In onNext()");
                    }
                });
    }

    public void findByFirstOrLastName(String key) {
        List<Person> newPeople = new ArrayList<>();
        for (Person person : mPeople) {
            if (person.getName().getFirstName().toLowerCase().contains(key.toLowerCase()) ||
                    person.getName().getLastName().toLowerCase().contains(key.toLowerCase())) {
                newPeople.add(person);
            }
        }
        mPeople = newPeople;
    }

    @Override
    public void getData(List<Person> people, String seed, String name) {
        mPeople = people;

        getPeople(seed);

        if (!name.equals("")) {
            findByFirstOrLastName(name);
        }
        people =  mPeople;
    }

    // TODO: 7/12/2019 getMethod for clear mSubscription
}
