package com.davidgarrido.myweather.API.Services;

import com.davidgarrido.myweather.Models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Usuario on 08/08/2018.
 */

public interface WeatherServiceLoc {
    @GET("weather")
    Call<City> getCity(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String key, @Query("units") String value, @Query("lang") String lang);
}
