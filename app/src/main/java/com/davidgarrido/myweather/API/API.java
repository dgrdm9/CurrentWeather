package com.davidgarrido.myweather.API;

import com.davidgarrido.myweather.Models.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usuario on 08/08/2018.
 */

public class API {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_ICONS = "http://openweathermap.org/img/w/";
    public static final String EXTENSION_ICONS = ".png";

    private static Retrofit retrofit = null;

    public static final String APPKEY = "3b0162c720e98c78fa489339ec1118ac";

    public static Retrofit getApi() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class, new MyDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }

    public static String convertTime(double unixTime) {
        double number = unixTime / 86400.0;
        String str = String.valueOf(number);
        float number2 = Float.parseFloat(str.substring(str.indexOf('.')));
        float number3 = number2 * 24;
        String str3 = String.valueOf(number3);
        int hour = Integer.parseInt(str3.substring(0, str3.indexOf('.'))) +2;
        float number4 = Float.parseFloat(str3.substring(str3.indexOf('.')));
        float number5 = number4 * 60;
        String str5 = String.valueOf(number5);
        int min = Integer.parseInt(str5.substring(0, str5.indexOf('.')));

        if (hour < 0){
            hour = 24 + hour;
        }
        if (hour > 24){
            hour = hour - 24;
        }


        String time;
        if (Integer.toString(hour).length() == 1 && Integer.toString(min).length() == 1) {
            time = "0" + hour + ":0" + min;
        } else if (Integer.toString(hour).length() == 2 && Integer.toString(min).length() == 1) {
            time = hour + ":0" + min;
        } else if (Integer.toString(hour).length() == 1 && Integer.toString(min).length() == 2) {
            time = "0" + hour + ":" + min;
        } else {
            time = hour + ":" + min;
        }
        return time;
    }

    public static String convertDegToDir(int deg){
        String windDir = "Error";
        if (deg >= 0 && deg <= 22.5){
            windDir = "Norte";
        } else if (deg > 22.5 && deg <= 67.5) {
            windDir = "Nordeste";
        } else if (deg > 67.5 && deg <= 112.5) {
            windDir = "Este";
        }else if (deg > 112.5 && deg <= 157.5) {
            windDir = "Sudeste";
        }else if (deg > 157.5 && deg <= 202.5) {
            windDir = "Sur";
        }else if (deg > 202.5 && deg <= 247.5) {
            windDir = "Suroeste";
        }else if (deg > 247.5 && deg <= 292.5) {
            windDir = "Oeste";
        }else if (deg >= 292.5 && deg <= 337.5) {
            windDir = "Noroeste";
        }else if (deg >= 337.5 && deg <= 360) {
            windDir = "Norte";
        }else if (deg == 1000){
            windDir = "";
        }
        return windDir;
    }
}
