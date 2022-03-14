package com.example.mvpweather.model.weather_7days;

import java.io.Serializable;

public class Main implements Serializable {
    private float temp_min;
    private float temp_max;
    private int humidity;

    public Main(float temp_min, float temp_max, int humidity) {
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Main() {
    }
}
