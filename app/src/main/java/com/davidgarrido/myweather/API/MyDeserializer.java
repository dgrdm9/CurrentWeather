package com.davidgarrido.myweather.API;

import com.davidgarrido.myweather.Models.City;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Usuario on 08/08/2018.
 */

public class MyDeserializer implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String name = obj.get("name").getAsString();
        String country = obj.get("sys").getAsJsonObject().get("country").getAsString();
        double temp = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
        String description = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
        String icon = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        int tempMax = obj.get("main").getAsJsonObject().get("temp_max").getAsInt();
        int tempMin = obj.get("main").getAsJsonObject().get("temp_min").getAsInt();
        int humidity = obj.get("main").getAsJsonObject().get("humidity").getAsInt();
        double windSpeed = obj.get("wind").getAsJsonObject().get("speed").getAsDouble();
        int windDir;
        if(obj.get("wind").getAsJsonObject().get("deg") == null){
            windDir = 1000;
        }else{
            windDir = obj.get("wind").getAsJsonObject().get("deg").getAsInt();
        }
        double sunrise = obj.get("sys").getAsJsonObject().get("sunrise").getAsDouble();
        double sunset = obj.get("sys").getAsJsonObject().get("sunset").getAsDouble();

        return new City(name, country, temp, description, icon, tempMax, tempMin, humidity, windSpeed, windDir, sunrise, sunset);
    }
}
