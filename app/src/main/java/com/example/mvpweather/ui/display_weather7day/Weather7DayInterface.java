package com.example.mvpweather.ui.display_weather7day;

import com.example.mvpweather.model.weather_7days.Weather7day;
import com.example.mvpweather.model.weather_current.WeatherCurrent;

public interface Weather7DayInterface {
    void onSuccess(Weather7day data);
    void onFailed(String error);
}
