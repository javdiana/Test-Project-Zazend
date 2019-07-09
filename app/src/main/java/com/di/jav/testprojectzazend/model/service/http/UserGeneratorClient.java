package com.di.jav.testprojectzazend.model.service.http;

import com.di.jav.testprojectzazend.model.entity.Result;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class UserGeneratorClient {
    private static UserGeneratorClient sOutInstance;

    private IUserGeneratorService api;
    private static final String RANDOM_USER_BASE_URL = "https://randomuser.me/";


    public static UserGeneratorClient getInstance() {
        if (sOutInstance == null) {
            sOutInstance = new UserGeneratorClient();
        }
        return sOutInstance;
    }

    private UserGeneratorClient() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RANDOM_USER_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(IUserGeneratorService.class);
    }

    public Observable<Result> getPeople(int numberOfPersons, String seed) {
        return api.getPeople(numberOfPersons, seed);
    }
}
