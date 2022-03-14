package com.example.mvpweather.ui.display_home;

import com.example.mvpweather.model.weather_current.WeatherCurrent;

public interface HomeInterface {
    void onSuccess(WeatherCurrent data);
    void onFailed(String error);
}
