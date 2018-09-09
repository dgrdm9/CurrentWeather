package com.davidgarrido.myweather.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usuario on 08/08/2018.
 */

public class City {
    private String name;
    private String country;
    @SerializedName("temp")
    private double temperature;
    private String description;
    private String icon;
    @SerializedName("temp_max")
    private int tempMax;
    @SerializedName("temp_min")
    private int tempMin;
    private int humidity;
    @SerializedName("speed")
    private double windSpeed;
    @SerializedName("deg")
    private int windDir;
    private double sunrise;
    private double sunset;

    public City() {
    }

    public City(String name, String country, double temperature, String description, String icon, int tempMax, int tempMin, int humidity, double windSpeed, int windDir, double sunrise, double sunset) {
        this.name = name;
        this.country = country;
        this.temperature = temperature;
        this.description = description;
        this.icon = icon;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDir() {
        return windDir;
    }

    public void setWindDir(int windDir) {
        this.windDir = windDir;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }
}
