package com.davidgarrido.myweather.API.Services;

import com.davidgarrido.myweather.Models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Usuario on 14/08/2018.
 */

public interface WeatherServiceName {
    @GET("weather")
    Call<City> getCity(@Query("q") String name, @Query("appid") String key, @Query("units") String value, @Query("lang") String lang);
}
