package com.example.mvpweather.model.weather_7days;

import java.io.Serializable;
import java.util.List;

public class Weather7day implements Serializable {
    private City city;
    private List<Lists> list;

    public Weather7day() {
    }

    public Weather7day(City city, List<Lists> list) {
        this.city = city;
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Lists> getList() {
        return list;
    }

    public void setList(List<Lists> list) {
        this.list = list;
    }
}
