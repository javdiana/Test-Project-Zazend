package com.di.jav.testprojectzazend.model.service.http;

import com.di.jav.testprojectzazend.model.entity.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IUserGeneratorService {
    @GET("api")
    Observable<Result> getPeople(@Query("results") Integer results, @Query("seed") String seed);

    @GET("api")
    Observable<Result> getPersonFromName(@Query("name") String name);

    @GET("api")
    Observable<Result> getPersonFromSeed(@Query("seed") String seed);
}
