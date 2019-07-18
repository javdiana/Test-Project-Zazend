package com.di.jav.testprojectzazend.model.repository;

import android.util.Log;

import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.entity.Result;
import com.di.jav.testprojectzazend.model.service.http.UserGeneratorClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PersonRepository {
    private static final String TAG = PersonRepository.class.getSimpleName();
    private static final int NUMBER_OF_PERSONS = 10;

    private Subscription mSubscription;
    private List<Person> mPeople;

    public PersonRepository(List<Person> people) {
        mPeople = people;
    }

    private void getPeople(String seed) {
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
                        Log.d(TAG, "In onError() " + e.getMessage());
                    }

                    @Override
                    public void onNext(Result result) {
                        mPeople = Arrays.asList(result.getPerson());
                        Log.d(TAG, "In onNext()");
                    }
                });
    }

    public void clearSubscription() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public void loadData(){

    }

    public List<Person> getPeople() {
        return mPeople;
    }

    private void setData(List<Person> people){
        mPeople = people;
    }
}
