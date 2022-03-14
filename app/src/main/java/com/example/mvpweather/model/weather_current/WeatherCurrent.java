package com.example.mvpweather.model.weather_current;

import java.util.List;

public class WeatherCurrent {
    private String name;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private long dt;

    public WeatherCurrent() {
    }

    public WeatherCurrent(String name, List<Weather> weather, Main main, Wind wind) {
        this.name = name;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }

    public long getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
