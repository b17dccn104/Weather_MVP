package com.example.mvpweather.service;


import com.example.mvpweather.model.weather_7days.Weather7day;
import com.example.mvpweather.model.weather_current.WeatherCurrent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Dataservice {  //gui phuong thuc len Server va lay du lieu ve(de o dang interface)
    @GET("data/2.5/weather")
    Call<WeatherCurrent> getDataCurrentWeather(@Query("q") String city,
                                               @Query("appid") String apiKey);
    @GET("data/2.5/forecast")
    Call<Weather7day> getDataWeather7Day(@Query("q") String city,
                                          @Query("appid") String apiKey);
}
