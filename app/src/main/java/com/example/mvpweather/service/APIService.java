package com.example.mvpweather.service;
//https://openweathermap.org/current
public class APIService {
    private static String base_url = "http://api.openweathermap.org/";

    public  static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
